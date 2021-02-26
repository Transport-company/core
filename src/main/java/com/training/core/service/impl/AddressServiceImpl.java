package com.training.core.service.impl;

import com.training.core.exception.AddressNotFoundException;
import com.training.core.exception.ErrorMessages;
import com.training.core.model.Address;
import com.training.core.repository.AddressRepository;
import com.training.core.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Address getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the address with id: {}", id);
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(
                        "Not found the address with id " + id));
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Optional<Address> getOptionalByAddress(@NonNull Address address) {
        Assert.notNull(address, ErrorMessages.NULL_ADDRESS_OBJECT.getErrorMessage());

        log.info("Requested id for the address: {}", address);
        List<Address> list = addressRepository.findByCode(address.getCode());
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return list.stream()
                .filter(e -> e.equals(address))
                .findFirst();
    }

    @Transactional
    @NonNull
    @Override
    public Address save(@NonNull Address address) {
        Assert.notNull(address, ErrorMessages.NULL_ADDRESS_OBJECT.getErrorMessage());

        Address saved = addressRepository.save(address);
        log.info("Saved a new address with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public Address update(@NonNull Long id, @NonNull Address address) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(address, ErrorMessages.NULL_ADDRESS_OBJECT.getErrorMessage());

        Address fetched = getById(id);
        address.setId(fetched.getId());
        address.setCreated(fetched.getCreated());
        Address updated = addressRepository.save(address);
        log.info("Updated the address with id: {}", updated.getId());
        return updated;
    }

}
