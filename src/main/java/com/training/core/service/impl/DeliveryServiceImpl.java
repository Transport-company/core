package com.training.core.service.impl;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Address;
import com.training.core.model.Cargo;
import com.training.core.model.Client;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.DeliveryRepository;
import com.training.core.service.AddressService;
import com.training.core.service.CargoService;
import com.training.core.service.ClientService;
import com.training.core.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final AddressService addressService;
    private final CargoService cargoService;
    private final ClientService clientService;

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Page<Delivery> getList(@NonNull Pageable pageable) {
        log.info("Requested delivery page: {} page, {} size",
                pageable.getPageNumber(), pageable.getPageSize());
        return deliveryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Delivery getById(@NonNull Long id) {
        Assert.notNull(id, "Id can not be null");

        log.info("Requested the delivery with id: {}", id);
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the delivery with id " + id));
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public List<Delivery> getByStatus(@NonNull DeliveryStatus status) {
        Assert.notNull(status, "Status can not be null");

        log.info("Requested a delivery list filtered with status: {}", status);
        return deliveryRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public boolean existsTrackNumber(@NonNull String trackingNumber) {
        Assert.notNull(trackingNumber, "Tracking number can not be null");

        log.info("Requested a check for the tracking number: {}", trackingNumber);
        Optional<Delivery> deliveryOptional = deliveryRepository.findByTrackingNumber(trackingNumber);
        return deliveryOptional.isPresent();
    }

    @Transactional
    @Override
    @NonNull
    public Delivery save(@NonNull Delivery delivery) {
        Assert.notNull(delivery, "Delivery can not be null");

        complementAggregated(delivery);

        Delivery saved = deliveryRepository.save(delivery);
        log.info("Saved a new delivery with id: {}", saved.getId());
        return saved;
    }

    private void complementAggregated(Delivery delivery) {
        Client sender = delivery.getSender();
        delivery.setSender(refreshClient(sender));

        Client recipient = delivery.getRecipient();
        delivery.setRecipient(refreshClient(recipient));

        Address sendingAddress = delivery.getSendingAddress();
        Optional<Address> optionalSending = addressService.getOptionalByAddress(sendingAddress);
        delivery.setSendingAddress(optionalSending.orElseGet(() -> addressService.save(sendingAddress)));

        Address shippingAddress = delivery.getShippingAddress();
        Optional<Address> optionalShipping = addressService.getOptionalByAddress(shippingAddress);
        delivery.setShippingAddress(optionalShipping.orElseGet(() -> addressService.save(shippingAddress)));
    }

    private Client refreshClient(Client client) {
        Optional<Client> optionalClient = clientService.getOptionalByEmail(client.getEmail());
        if (optionalClient.isPresent()) {
            client.setId(optionalClient.get().getId());
            client.setCreated(optionalClient.get().getCreated());
            client.setUpdated(optionalClient.get().getUpdated());
            if (client.equals(optionalClient.get())) {
                return client;
            }
        }
        return clientService.save(client);
    }

    @Transactional
    @Override
    @NonNull
    public Delivery update(@NonNull Long id, @NonNull Delivery delivery) {
        Assert.notNull(id, "Id can not be null");
        Assert.notNull(delivery, "Delivery can not be null");

        Delivery fetched = getById(id);
        delivery.setId(fetched.getId());
        delivery.setCreated(fetched.getCreated());

        Cargo fetchedCargo = cargoService.getById(fetched.getCargo().getId());
        delivery.getCargo().setId(fetchedCargo.getId());
        delivery.getCargo().setCreated(fetchedCargo.getCreated());

        complementAggregated(delivery);

        delivery.setTracking(fetched.getTracking());

        Delivery updated = deliveryRepository.save(delivery);
        log.info("Updated the delivery with id: {}", updated.getId());
        return updated;
    }

    @Override
    public void changeStatus(@NonNull Long id, @NonNull DeliveryStatus status) {
        Assert.notNull(id, "Id can not be null");
        Assert.notNull(status, "Status can not be null");

        Delivery fetched = getById(id);
        fetched.setStatus(status);
        deliveryRepository.save(fetched);
        log.info("The status '{}' for the delivery with id {} is set", status, id);
    }

    @Transactional
    @Override
    @NonNull
    public void delete(@NonNull Long id) {
        Assert.notNull(id, "Id can not be null");

        getById(id);
        deliveryRepository.deleteById(id);
        log.info("Deleted the delivery with id: {}", id);
    }
}
