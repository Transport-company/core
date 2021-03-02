package com.training.core.service.impl;

import com.training.core.model.Cheque;
import com.training.core.model.Delivery;
import com.training.core.repository.ChequeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PaymentServiceImplTest extends BaseTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    DeliveryServiceImpl deliveryService;

    @Mock
    private ChequeRepository chequeRepository;

    private List<Cheque> testList;

    @BeforeEach
    void setUp() {
        prepareTestData();
    }

    private void prepareTestData() {
        testList = new ArrayList<>();
        testList.add(Cheque.builder()
                .id(1L)
                .sum(new BigDecimal(10))
                .build());
        testList.add(Cheque.builder()
                .id(2L)
                .sum(new BigDecimal(20))
                .build());
    }

    @Test
    void create_returnCheque() {
        Delivery delivery = Delivery.builder().id(1L).build();
        Cheque cheque = Cheque.builder()
                .sum(new BigDecimal(400))
                .delivery(delivery)
                .chequeFile(new byte[0])
                .build();

        when(deliveryService.getById(cheque.getDelivery().getId())).thenReturn(delivery);
        when(chequeRepository.save(cheque)).thenReturn(cheque);
        assertNotNull(paymentService.create(cheque));
    }

    @Test
    void getList_returnChequeList() {
        int pageSize = 2;
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        when(paymentService.getList(pageRequest)).thenReturn(new PageImpl<>(testList));
        Page<Cheque> page = paymentService.getList(pageRequest);

        assertNotNull(page);
        assertEquals(testList.size(), page.getTotalElements());
    }

    @Test
    void getById_returnCheque() {
        Long id = 1L;
        int index = 0;

        when(chequeRepository.findById(id)).thenReturn(Optional.of(testList.get(index)));

        Cheque cheque = paymentService.getById(id);

        assertNotNull(cheque);
        assertEquals(testList.get(index), cheque);
    }
}
