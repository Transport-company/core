package com.training.core.service;

import com.training.core.model.Address;
import com.training.core.model.Cargo;
import com.training.core.model.Client;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.model.Tracking;
import com.training.core.service.impl.DeliveryServiceImpl;
import com.training.core.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    DeliveryServiceImpl deliveryService;

    @Mock
    DeliverySumCalculatingService sumCalculatingService;

    private static List<Delivery> testList;
    private static Delivery testDelivery;
    private static Set<Tracking> testTracking;
    private static Cargo testCargo;
    private static Client testSender;
    private static Client testRecipient;
    private static Address testSendingAddress;
    private static Address testShippingAddress;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @BeforeAll
    private static void prepareTestData() {
        testTracking = new HashSet<>();
        testTracking.add(new Tracking());

        testCargo = new Cargo();
        testCargo.setId(1L);
        testCargo.setDeclaredValue(new BigDecimal(15.3));
        testCargo.setWeight(0.1f);
        testCargo.setWidth(1.5f);
        testCargo.setLength(1.5f);
        testCargo.setHeight(1.5f);

        testSender = new Client();
        testSender.setId(1L);
        testSender.setLastName("Ivanov");
        testSender.setFirstName("Ivan");
        testSender.setMiddleName("Ivanovich");
        testSender.setBirthday(LocalDate.of(2020,12,12));
        testSender.setEmail("Ivanov@gmail.com");
        testSender.setPhoneNumber("99999999999");

        testRecipient = new Client();
        testRecipient.setId(2L);
        testRecipient.setLastName("Sidorov");
        testRecipient.setFirstName("Ivan");
        testRecipient.setMiddleName("Ivanovich");
        testRecipient.setBirthday(LocalDate.of(2019,11,11));
        testRecipient.setEmail("Sidorov@gmail.com");
        testRecipient.setPhoneNumber("88888888888");

        testSendingAddress = new Address();
        testSendingAddress.setId(1L);
        testSendingAddress.setRegion("Ulyanovsk region");
        testSendingAddress.setCity("Ulyanovsk");
        testSendingAddress.setStreet("Lenina");
        testSendingAddress.setHouse("15");
        testSendingAddress.setApartment("11B");
        testSendingAddress.setCode(1111);

        testShippingAddress = new Address();
        testShippingAddress.setId(2L);
        testShippingAddress.setRegion("Ulyanovsk region");
        testShippingAddress.setCity("Ulyanovsk");
        testShippingAddress.setStreet("Lenina");
        testShippingAddress.setHouse("20");
        testShippingAddress.setApartment("1");
        testShippingAddress.setCode(2222);

        testDelivery = new Delivery();
        testDelivery.setId(1L);
        testDelivery.setEnabledNotifications(false);
        testDelivery.setSum(new BigDecimal(15.0));
        testDelivery.setTrackingNumber("1a");
        testDelivery.setTracking(testTracking);
        testDelivery.setIsPaid(true);
        testDelivery.setStatus(DeliveryStatus.REGISTERED);
        testDelivery.setCargo(testCargo);
        testDelivery.setSender(testSender);
        testDelivery.setRecipient(testRecipient);
        testDelivery.setSendingAddress(testSendingAddress);
        testDelivery.setShippingAddress(testShippingAddress);

        testList = new ArrayList<>();
        testList.add(testDelivery);
    }


    @Test
    void getListTest() {
        int pageSize = 2;
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        when(deliveryService.getList(pageRequest))
                .thenReturn(new PageImpl<>(testList));

        Page<Delivery> page = orderService.getList(pageRequest);

        assertNotNull(page);
        assertEquals(testList.size(), page.getTotalElements());
    }

    @Test
    void getByIdTest() {
        Long id = 1L;

        when(deliveryService.getById(id)).thenReturn(testDelivery);
        Delivery delivery = orderService.getById(id);

        assertNotNull(delivery);
        assertEquals(testDelivery, delivery);
    }

    @Test
    void createTest() {
        int size = testList.size();

        Delivery delivery = new Delivery();
        delivery.setSum(new BigDecimal(15));

        when(deliveryService.save(any()))
                .thenAnswer((Answer<Delivery>) i ->{
                    testList.add(i.getArgument(0));
                    return i.getArgument(0);
                });

        orderService.create(delivery);

        assertEquals(size + 1, testList.size());
        assertEquals(delivery.getSum(), testList.get(testList.size() - 1).getSum());
    }

    @Test
    void updateTest() {
        long id = 1L;

        Delivery delivery = testDelivery;
        delivery.setSum(new BigDecimal(150));

        when(deliveryService.update(id, delivery))
                .thenReturn(delivery);

        Delivery updateDelivery = orderService.update(id, delivery);

        assertNotNull(updateDelivery);
        assertEquals(updateDelivery.getSum(), delivery.getSum());
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        int size = testList.size();

        doAnswer(i -> {
            testList.remove(size - 1);
            return null;
        }).when(deliveryService).delete(id);

        orderService.delete(id);

        assertEquals(size - 1, testList.size());
    }

}
