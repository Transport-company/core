package com.training.core.service.impl;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.DeliveryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
import static org.mockito.Mockito.when;

class DeliveryServiceImplTest {

    private MockitoSession mockitoSession;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    private List<Delivery> testData;
    private String exitingTrackingNumber = "1j3dfTy48GaeUI41l0gsBya5FhGTq6";

    @BeforeEach
    void setUp() {
        mockitoSession = Mockito.mockitoSession()
                .initMocks(this)
                .strictness(Strictness.STRICT_STUBS)
                .startMocking();
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
    }

    @AfterEach
    void tearDown() {
        mockitoSession.finishMocking();
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
        String freeTrackingNumber = "dfrTR6wmp0u7Y6b3fdTa6q1nNhsz57";

        when(deliveryRepository.findByTrackingNumber(any()))
                .thenAnswer(invocation -> testData.stream()
                            .filter(e -> invocation.getArgument(0).equals(e.getTrackingNumber()))
                            .findAny());

        assertTrue(deliveryService.existsTrackingNumber(exitingTrackingNumber));
        assertFalse(deliveryService.existsTrackingNumber(freeTrackingNumber));
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void changeStatus() {
    }

    @Test
    void delete() {
    }
}