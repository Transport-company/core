package com.training.core.service.impl;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Address;
import com.training.core.model.Cargo;
import com.training.core.model.Client;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.DeliveryRepository;
import com.training.core.service.AddressService;
import com.training.core.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class DeliveryServiceImplTest extends BaseTest {

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private ClientService clientService;

    private List<Delivery> testData;
    private final String exitingTrackingNumber = "1j3dfTy48GaeUI41l0gsBya5FhGTq6";

    @BeforeEach
    void setUp() {
        prepareTestData();
    }

    private void prepareTestData() {
        testData = new ArrayList<>();
        testData.add(Delivery.builder()
                .id(1L)
                .status(DeliveryStatus.REGISTERED)
                .isPaid(false)
                .build());
        testData.add(Delivery.builder()
                .id(2L)
                .status(DeliveryStatus.PAID)
                .isPaid(true)
                .trackingNumber(exitingTrackingNumber)
                .build());
        testData.add(Delivery.builder()
                .id(3L)
                .status(DeliveryStatus.REGISTERED)
                .isPaid(false)
                .build());
    }

    @Test
    void getList() {
        int pageSize = 10;
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        when(deliveryRepository.findAll(pageRequest))
                .thenReturn(
                        new PageImpl<>(
                                pageSize < testData.size()
                                        ? testData.stream()
                                        .limit(pageSize)
                                        .collect(Collectors.toList())
                                        : testData,
                                pageRequest,
                                testData.size()));

        Page<Delivery> page = deliveryService.getList(pageRequest);

        assertNotNull(page);
        assertEquals(Math.min(pageSize, testData.size()), page.getContent().size());
        assertEquals(testData.size(), page.getTotalElements());
    }

    @Test
    void getById() {
        Long id = 1L;
        int index = 0;

        when(deliveryRepository.findById(id))
                .thenReturn(Optional.of(testData.get(index)));

        Delivery delivery = deliveryService.getById(id);

        assertNotNull(delivery);
        assertEquals(testData.get(index), delivery);
    }

    @Test
    void getByIdNotFound() {
        Long id = 999L;

        when(deliveryRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> deliveryService.getById(id));
    }

    @Test
    void getByStatus() {
        DeliveryStatus status = DeliveryStatus.REGISTERED;
        List<Delivery> list = testData.stream()
                .filter(e -> e.getStatus().equals(status))
                .collect(Collectors.toList());

        when(deliveryRepository.findByStatus(status))
                .thenReturn(list);

        List<Delivery> byStatus = deliveryService.getByStatus(status);

        assertNotNull(byStatus);
        assertEquals(list, byStatus);
    }

    @Test
    void isPaid() {
        Long id1 = 1L;
        int index1 = 0;
        Long id2 = 2L;
        int index2 = 1;

        when(deliveryRepository.findById(id1))
                .thenReturn(Optional.of(testData.get(index1)));
        when(deliveryRepository.findById(id2))
                .thenReturn(Optional.of(testData.get(index2)));

        assertFalse(deliveryService.isPaid(id1));
        assertTrue(deliveryService.isPaid(id2));
    }

    @Test
    void existsTrackingNumber() {
        final String freeTrackingNumber = "dfrTR6wmp0u7Y6b3fdTa6q1nNhsz57";

        when(deliveryRepository.findByTrackingNumber(any()))
                .thenAnswer(invocation -> testData.stream()
                        .filter(e -> invocation.getArgument(0).equals(e.getTrackingNumber()))
                        .findAny());

        assertTrue(deliveryService.existsTrackingNumber(exitingTrackingNumber));
        assertFalse(deliveryService.existsTrackingNumber(freeTrackingNumber));
    }

    @Test
    void save() {
        Client sender = getSender();
        Client recipient = getRecipient();
        Address sendingAddress = getSendingAddress();
        Address shippingAddress = getShippingAddress();
        Delivery delivery = Delivery.builder()
                .sender(sender)
                .recipient(recipient)
                .sendingAddress(sendingAddress)
                .shippingAddress(shippingAddress)
                .build();

        when(clientService.getOptionalByEmail(sender.getEmail()))
                .thenReturn(Optional.of(sender));
        when(clientService.getOptionalByEmail(recipient.getEmail()))
                .thenReturn(Optional.of(recipient));
        when(addressService.getOptionalByAddress(sendingAddress))
                .thenReturn(Optional.of(sendingAddress));
        when(addressService.getOptionalByAddress(shippingAddress))
                .thenReturn(Optional.of(shippingAddress));
        when(deliveryRepository.save(delivery))
                .thenReturn(delivery);

        assertNotNull(deliveryService.save(delivery));
    }

    private Client getSender() {
        return Client.builder()
                .id(1L)
                .email("sender@gmail.com")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
    }

    private Client getRecipient() {
        return Client.builder()
                .id(2L)
                .email("recipient@gmail.com")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
    }

    private Address getSendingAddress() {
        Address address = Address.builder()
                .id(1L)
                .region("Sening region")
                .city("Sending city")
                .house("1A")
                .code(1)
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
        address.setCode(address.hashCode());

        return address;
    }

    private Address getShippingAddress() {
        Address address = Address.builder()
                .id(2L)
                .region("Shipping region")
                .city("Shipping city")
                .house("2B")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
        address.setCode(address.hashCode());

        return address;
    }

    @Test
    void update() {
        Client sender = getSender();
        Client recipient = getRecipient();
        Address sendingAddress = getSendingAddress();
        Address shippingAddress = getShippingAddress();
        Cargo fetchedCargo = Cargo.builder()
                .id(1L)
                .created(LocalDateTime.now())
                .build();
        Long id = 73L;
        Delivery fetched = Delivery.builder()
                .id(id)
                .cargo(fetchedCargo)
                .sender(sender)
                .recipient(recipient)
                .sendingAddress(sendingAddress)
                .shippingAddress(shippingAddress)
                .created(LocalDateTime.now())
                .build();
        Delivery delivery = Delivery.builder()
                .cargo(new Cargo())
                .sender(sender)
                .recipient(recipient)
                .sendingAddress(sendingAddress)
                .shippingAddress(shippingAddress)
                .build();

        when(clientService.getOptionalByEmail(sender.getEmail()))
                .thenReturn(Optional.of(sender));
        when(clientService.getOptionalByEmail(recipient.getEmail()))
                .thenReturn(Optional.of(recipient));
        when(addressService.getOptionalByAddress(sendingAddress))
                .thenReturn(Optional.of(sendingAddress));
        when(addressService.getOptionalByAddress(shippingAddress))
                .thenReturn(Optional.of(shippingAddress));
        when(deliveryRepository.findById(id))
                .thenReturn(Optional.of(fetched));
        when(deliveryRepository.save(delivery))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Delivery saved = deliveryService.update(id, delivery);

        assertNotNull(saved);
        assertEquals(id, saved.getId());
        assertEquals(fetched.getCreated(), saved.getCreated());
        assertEquals(fetched.getCargo().getId(), saved.getCargo().getId());
        assertEquals(fetched.getCargo().getCreated(), saved.getCargo().getCreated());
    }

    @Test
    void changeStatus() {
        DeliveryStatus oldStatus = DeliveryStatus.REGISTERED;
        DeliveryStatus newStatus = DeliveryStatus.PAID;
        Long id = 73L;
        Delivery delivery = Delivery.builder()
                .id(id)
                .status(oldStatus)
                .build();

        when(deliveryRepository.findById(id))
                .thenReturn(Optional.of(delivery));
        when(deliveryRepository.save(delivery))
                .thenAnswer(invocation -> invocation.getArgument(0));

        deliveryService.changeStatus(id, newStatus);

        assertEquals(newStatus, delivery.getStatus());
    }

    @Test
    void delete() {
        Long id = 103L;
        Delivery delivery = Delivery.builder()
                .id(id)
                .status(DeliveryStatus.REGISTERED)
                .isPaid(false)
                .build();
        testData.add(delivery);
        int testDataSizeBefore = testData.size();
        int index = testDataSizeBefore - 1;

        when(deliveryRepository.findById(id))
                .thenReturn(Optional.of(delivery));
        doAnswer(invocation -> {
            testData.remove(index);
            return null;
        }).when(deliveryRepository).deleteById(id);

        deliveryService.delete(id);

        assertEquals(testDataSizeBefore - 1, testData.size());
    }
}