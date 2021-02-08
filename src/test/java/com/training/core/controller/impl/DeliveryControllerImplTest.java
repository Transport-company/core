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
import com.training.core.dto.request.DeliveryStatusRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static int deliveryCount = 0;
    private static int deliveryCountPaid = 0;
    private static int addressCount = 0;
    private static int clientCount = 0;
    private static Long deliveryId1 = null;

    @Test
    @Order(1)
    void create1() throws Exception {
        DeliveryRequest request = getDelivery1(true);

        LocalDateTime start = LocalDateTime.now();

        MvcResult result = mockMvc.perform(post(Urls.Deliveries.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        deliveryCount++;
        addressCount += 2;
        clientCount += 2;

        assertEquals(deliveryCount, deliveryRepository.findAll().size());
        assertEquals(deliveryCount, cargoRepository.findAll().size());
        assertEquals(clientCount, clientRepository.findAll().size());
        assertEquals(addressCount, addressRepository.findAll().size());

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());

        deliveryId1 = response.getId();

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

    private DeliveryRequest getDelivery1(boolean enabledNotifications) {
        return DeliveryRequest.builder()
                .enabledNotifications(enabledNotifications)
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
    void delete1() throws Exception {
        mockMvc.perform(delete(Urls.Deliveries.FULL + "/" + deliveryId1))
                .andDo(print())
                .andExpect(status().isOk());

        deliveryCount--;
        deliveryId1 = null;

        assertEquals(deliveryCount, deliveryRepository.findAll().size());
        assertEquals(deliveryCount, cargoRepository.findAll().size());
        assertEquals(clientCount, clientRepository.findAll().size());
        assertEquals(addressCount, addressRepository.findAll().size());
    }

    @Test
    @Order(3)
    void create1Again() throws Exception {
        DeliveryRequest request = getDelivery1(true);

        MvcResult result = mockMvc.perform(post(Urls.Deliveries.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        deliveryCount++;

        assertEquals(deliveryCount, deliveryRepository.findAll().size());
        assertEquals(deliveryCount, cargoRepository.findAll().size());
        assertEquals(2, clientRepository.findAll().size());
        assertEquals(2, addressRepository.findAll().size());

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());

        deliveryId1 = response.getId();
    }

    @Test
    @Order(4)
    void create2() throws Exception {
        DeliveryRequest request = getDelivery2();

        MvcResult result = mockMvc.perform(post(Urls.Deliveries.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        deliveryCount++;
        addressCount++;
        clientCount++;

        assertEquals(deliveryCount, deliveryRepository.findAll().size());
        assertEquals(deliveryCount, cargoRepository.findAll().size());
        assertEquals(clientCount, clientRepository.findAll().size());
        assertEquals(addressCount, addressRepository.findAll().size());

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());
    }

    private DeliveryRequest getDelivery2() {
        return DeliveryRequest.builder()
                .enabledNotifications(true)
                .sum(new BigDecimal("0.00"))
                .isPaid(false)
                .status(DeliveryStatus.REGISTERED)
                .cargo(getCargo2())
                .sender(getSender1())
                .recipient(getRecipient2())
                .sendingAddress(getSendingAdress1())
                .shippingAddress(getShippingAdress2())
                .build();
    }

    private CargoRequest getCargo2() {
        return CargoRequest.builder()
                .weight(1.2f)
                .declaredValue(new BigDecimal("1500.00"))
                .length(25f)
                .width(12f)
                .height(10f)
                .build();
    }

    private ClientRequest getRecipient2() {
        return ClientRequest.builder()
                .lastName("Cvetkov")
                .firstName("Vitaliy")
                .middleName("Sergeevich")
                .birthday(LocalDate.of(1980, 4, 27))
                .phoneNumber("89003450002")
                .email("Cvetkov_VS@gmail.com")
                .build();
    }

    private AddressRequest getShippingAdress2() {
        return AddressRequest.builder()
                .region("Kostromskaya oblast")
                .city("Kostroma")
                .street("Lesnaya")
                .house("40")
                .apartment("3")
                .build();
    }

    @Test
    @Order(5)
    void update() throws Exception {
        DeliveryRequest request = getDelivery1(false);

        LocalDateTime start = LocalDateTime.now();

        MvcResult result = mockMvc.perform(put(Urls.Deliveries.FULL + "/" + deliveryId1)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(deliveryCount, deliveryRepository.findAll().size());
        assertEquals(deliveryCount, cargoRepository.findAll().size());
        assertEquals(clientCount, clientRepository.findAll().size());
        assertEquals(addressCount, addressRepository.findAll().size());

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertEquals(request.getEnabledNotifications(), response.getEnabledNotifications());

        assertNotNull(response.getUpdated());
        assertTrue(start.isBefore(response.getUpdated())
                && LocalDateTime.now().isAfter(response.getUpdated()));
    }

    @Test
    @Order(6)
    void changeStatus() throws Exception {
        DeliveryStatus status = DeliveryStatus.PAID;
        DeliveryStatusRequest statusRequest = new DeliveryStatusRequest(status);

        LocalDateTime start = LocalDateTime.now();

        MvcResult result = mockMvc.perform(
                patch(Urls.Deliveries.FULL + "/" + deliveryId1 + "/" + Urls.Deliveries.Status.PART)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(statusRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        deliveryCountPaid++;

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertEquals(status, response.getStatus());

        assertNotNull(response.getUpdated());
        assertTrue(start.isBefore(response.getUpdated())
                && LocalDateTime.now().isAfter(response.getUpdated()));
    }

    @Test
    @Order(7)
    void getList() throws Exception {
        MvcResult result = mockMvc.perform(get(Urls.Deliveries.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        DeliveryPageResponse response = objectMapper.readValue(content, DeliveryPageResponse.class);

        assertNotNull(response);
        assertEquals(deliveryCount, response.getTotalElements());

        List<DeliveryResponse> list = response.getContent();
        assertNotNull(list);
        assertEquals(deliveryCount, list.size());
    }

    @Test
    @Order(8)
    void getById() throws Exception {
        MvcResult result = mockMvc.perform(get(Urls.Deliveries.FULL + "/" + deliveryId1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);

        DeliveryRequest deliveryRequest1 = getDelivery1(false);
        assertEquals(deliveryRequest1.getCargo().getWeight(), response.getCargo().getWeight());
        assertEquals(deliveryRequest1.getCargo().getDeclaredValue(), response.getCargo().getDeclaredValue());
        assertEquals(deliveryRequest1.getCargo().getWidth(), response.getCargo().getWidth());
        assertEquals(deliveryRequest1.getCargo().getLength(), response.getCargo().getLength());
        assertEquals(deliveryRequest1.getCargo().getHeight(), response.getCargo().getHeight());

        assertEquals(deliveryRequest1.getSender().getEmail(), response.getSender().getEmail());
        assertEquals(deliveryRequest1.getRecipient().getEmail(), response.getRecipient().getEmail());
    }

    @Test
    @Order(9)
    void getFilteredList() throws Exception {
        MvcResult result = mockMvc.perform(
                get(Urls.Deliveries.Filter.FULL + "?status=PAID"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<DeliveryResponse> response = objectMapper.readValue(content,
                objectMapper
                        .getTypeFactory()
                        .constructCollectionType(List.class, DeliveryResponse.class));

        assertNotNull(response);
        assertEquals(deliveryCountPaid, response.size());

        if (deliveryCountPaid > 0) {
            assertEquals(DeliveryStatus.PAID, response.get(0).getStatus());
        }
    }
}