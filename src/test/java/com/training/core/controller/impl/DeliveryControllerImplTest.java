package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.request.AddressRequest;
import com.training.core.dto.request.CargoRequest;
import com.training.core.dto.request.ClientRequest;
import com.training.core.dto.request.DeliveryRequest;
import com.training.core.dto.response.DeliveryPageResponse;
import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.AddressRepository;
import com.training.core.repository.CargoRepository;
import com.training.core.repository.ClientRepository;
import com.training.core.repository.DeliveryRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeliveryControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    @Order(1)
    void create1() throws Exception {
        DeliveryRequest request = getDelivery1();

        LocalDateTime start = LocalDateTime.now();

        MvcResult result = mockMvc.perform(post(Urls.Deliveries.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(1, deliveryRepository.findAll().size());
        assertEquals(1, cargoRepository.findAll().size());
        assertEquals(2, clientRepository.findAll().size());
        assertEquals(2, addressRepository.findAll().size());

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());

        assertEquals(request.getEnabledNotifications(), response.getEnabledNotifications());
        assertEquals(request.getSum(), response.getSum());
        assertEquals(request.getIsPaid(), response.getIsPaid());
        assertEquals(request.getSum(), response.getSum());

        assertNotNull(response.getCargo());
        assertNotNull(response.getCargo().getId());
        assertEquals(request.getCargo().getWeight(), response.getCargo().getWeight());
        assertEquals(request.getCargo().getDeclaredValue(), response.getCargo().getDeclaredValue());
        assertEquals(request.getCargo().getLength(), response.getCargo().getLength());
        assertEquals(request.getCargo().getWidth(), response.getCargo().getWidth());
        assertEquals(request.getCargo().getHeight(), response.getCargo().getHeight());

        assertNotNull(response.getSender());
        assertNotNull(response.getSender().getId());
        assertEquals(request.getSender().getEmail(), response.getSender().getEmail());
        assertNotNull(response.getRecipient());
        assertNotNull(response.getRecipient().getId());
        assertEquals(request.getRecipient().getEmail(), response.getRecipient().getEmail());

        assertNotNull(response.getSendingAddress());
        assertNotNull(response.getSendingAddress().getId());
        assertEquals(request.getSendingAddress().getRegion(), response.getSendingAddress().getRegion());
        assertEquals(request.getSendingAddress().getCity(), response.getSendingAddress().getCity());
        assertEquals(request.getSendingAddress().getStreet(), response.getSendingAddress().getStreet());
        assertEquals(request.getSendingAddress().getHouse(), response.getSendingAddress().getHouse());
        assertEquals(request.getSendingAddress().getApartment(), response.getSendingAddress().getApartment());
        assertNotNull(response.getShippingAddress());
        assertNotNull(response.getShippingAddress().getId());
        assertEquals(request.getShippingAddress().getRegion(), response.getShippingAddress().getRegion());
        assertEquals(request.getShippingAddress().getCity(), response.getShippingAddress().getCity());
        assertEquals(request.getShippingAddress().getStreet(), response.getShippingAddress().getStreet());
        assertEquals(request.getShippingAddress().getHouse(), response.getShippingAddress().getHouse());
        assertEquals(request.getShippingAddress().getApartment(), response.getShippingAddress().getApartment());

        assertNotNull(response.getCreated());
        assertTrue(start.isBefore(response.getCreated())
                && LocalDateTime.now().isAfter(response.getCreated()));
        assertNotNull(response.getUpdated());
        assertTrue(start.isBefore(response.getUpdated())
                && LocalDateTime.now().isAfter(response.getUpdated()));
    }

    private DeliveryRequest getDelivery1() {
        return DeliveryRequest.builder()
                .enabledNotifications(true)
                .sum(new BigDecimal("0.00"))
                .isPaid(false)
                .status(DeliveryStatus.REGISTERED)
                .cargo(getCargo1())
                .sender(getSender1())
                .recipient(getRecipient1())
                .sendingAddress(getSendingAdress1())
                .shippingAddress(getShippingAdress1())
                .build();
    }

    private CargoRequest getCargo1() {
        return CargoRequest.builder()
                .weight(0.4f)
                .declaredValue(new BigDecimal("1000.00"))
                .length(15f)
                .width(10f)
                .height(5f)
                .build();
    }

    private ClientRequest getSender1() {
        return ClientRequest.builder()
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthday(LocalDate.of(1970, 11, 20))
                .phoneNumber("80123456789")
                .email("Ivanov_II@gmail.com")
                .build();
    }

    private ClientRequest getRecipient1() {
        return ClientRequest.builder()
                .lastName("Romashkin")
                .firstName("Igor")
                .middleName("Valentinovich")
                .birthday(LocalDate.of(1972, 9, 12))
                .phoneNumber("80123450001")
                .email("Romashkin_IV@gmail.com")
                .build();
    }

    private AddressRequest getSendingAdress1() {
        return AddressRequest.builder()
                .region("Krasnodarskiy kray")
                .city("Krrasnodar")
                .street("Krasnaya")
                .house("12")
                .apartment("1")
                .build();
    }

    private AddressRequest getShippingAdress1() {
        return AddressRequest.builder()
                .region("Nizhegorodskaya oblast")
                .city("Nizhny Novgorod")
                .street("Artelnaya")
                .house("58")
                .apartment("30")
                .build();
    }

    @Test
    @Order(2)
    void getList() throws Exception {
        MvcResult result = mockMvc.perform(get(Urls.Deliveries.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        DeliveryPageResponse response = objectMapper.readValue(content, DeliveryPageResponse.class);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());

        List<DeliveryResponse> list = response.getContent();
        assertNotNull(list);
        assertEquals(1, list.size());
    }

    @Test
    void getById() {
    }

    @Test
    void getFilteredList() {
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