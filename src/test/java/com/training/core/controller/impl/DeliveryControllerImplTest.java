package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.request.DeliveryRequest;
import com.training.core.dto.request.DeliveryStatusRequest;
import com.training.core.dto.response.DeliveryPageResponse;
import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.AddressRepository;
import com.training.core.repository.CargoRepository;
import com.training.core.repository.ClientRepository;
import com.training.core.repository.DeliveryRepository;
import com.training.core.util.TestDeliveryRequests;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
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
    void givenNotNullRequest_whenCreateFirstDelivery_thenResponseStatusCreated() throws Exception {
        DeliveryRequest request = TestDeliveryRequests.first(true);

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

        assertAll("The number of records in the repositories does not match the expected number",
                () -> assertEquals(deliveryCount, deliveryRepository.findAll().size()),
                () -> assertEquals(deliveryCount, cargoRepository.findAll().size()),
                () -> assertEquals(clientCount, clientRepository.findAll().size()),
                () -> assertEquals(addressCount, addressRepository.findAll().size())
        );

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());

        deliveryId1 = response.getId();

        assertAll("The passed values of the delivery fields do not match the values " +
                        "in the saved object",
                () -> assertEquals(request.getEnabledNotifications(),
                        response.getEnabledNotifications()),
                () -> assertEquals(request.getSum(), response.getSum()),
                () -> assertEquals(request.getIsPaid(), response.getIsPaid()),
                () -> assertEquals(request.getStatus(), response.getStatus())
        );

        assertNotNull(response.getCargo());
        assertNotNull(response.getCargo().getId());
        assertAll("The passed values of the cargo fields do not match the values " +
                        "in the saved object",
                () -> assertEquals(request.getCargo().getWeight(),
                        response.getCargo().getWeight()),
                () -> assertEquals(request.getCargo().getDeclaredValue(),
                        response.getCargo().getDeclaredValue()),
                () -> assertEquals(request.getCargo().getLength(),
                        response.getCargo().getLength()),
                () -> assertEquals(request.getCargo().getWidth(),
                        response.getCargo().getWidth()),
                () -> assertEquals(request.getCargo().getHeight(),
                        response.getCargo().getHeight())
        );

        assertNotNull(response.getSender());
        assertNotNull(response.getSender().getId());
        assertEquals(request.getSender().getEmail(), response.getSender().getEmail());
        assertNotNull(response.getRecipient());
        assertNotNull(response.getRecipient().getId());
        assertEquals(request.getRecipient().getEmail(), response.getRecipient().getEmail());

        assertNotNull(response.getSendingAddress());
        assertNotNull(response.getSendingAddress().getId());
        assertAll("The passed values of the sending address fields do not match the values " +
                        "in the saved object",
                () -> assertEquals(request.getSendingAddress().getRegion(),
                        response.getSendingAddress().getRegion()),
                () -> assertEquals(request.getSendingAddress().getCity(),
                        response.getSendingAddress().getCity()),
                () -> assertEquals(request.getSendingAddress().getStreet(),
                        response.getSendingAddress().getStreet()),
                () -> assertEquals(request.getSendingAddress().getHouse(),
                        response.getSendingAddress().getHouse()),
                () -> assertEquals(request.getSendingAddress().getApartment(),
                        response.getSendingAddress().getApartment())
        );
        assertNotNull(response.getShippingAddress());
        assertNotNull(response.getShippingAddress().getId());
        assertAll("The passed values of the shipping address fields do not match the values " +
                        "in the saved object",
                () -> assertEquals(request.getShippingAddress().getRegion(),
                        response.getShippingAddress().getRegion()),
                () -> assertEquals(request.getShippingAddress().getCity(),
                        response.getShippingAddress().getCity()),
                () -> assertEquals(request.getShippingAddress().getStreet(),
                        response.getShippingAddress().getStreet()),
                () -> assertEquals(request.getShippingAddress().getHouse(),
                        response.getShippingAddress().getHouse()),
                () -> assertEquals(request.getShippingAddress().getApartment(),
                        response.getShippingAddress().getApartment())
        );

        assertNotNull(response.getCreated());
        assertTrue(start.isBefore(response.getCreated())
                && LocalDateTime.now().isAfter(response.getCreated()));
        assertNotNull(response.getUpdated());
        assertTrue(start.isBefore(response.getUpdated())
                && LocalDateTime.now().isAfter(response.getUpdated()));
    }

    @Test
    @Order(2)
    void givenNotNullId_whenDeleteFirstDelivery_thenResponseStatusOk() throws Exception {
        mockMvc.perform(delete(Urls.Deliveries.FULL + "/" + deliveryId1))
                .andDo(print())
                .andExpect(status().isOk());

        deliveryCount--;
        deliveryId1 = null;

        assertAll("The number of records in the repositories does not match the expected number",
                () -> assertEquals(deliveryCount, deliveryRepository.findAll().size()),
                () -> assertEquals(deliveryCount, cargoRepository.findAll().size()),
                () -> assertEquals(clientCount, clientRepository.findAll().size()),
                () -> assertEquals(addressCount, addressRepository.findAll().size())
        );
    }

    @Test
    @Order(3)
    void givenNotNullRequest_whenCreateFirstDeliveryAgain_thenResponseStatusCreated()
            throws Exception {
        DeliveryRequest request = TestDeliveryRequests.first(true);

        MvcResult result = mockMvc.perform(post(Urls.Deliveries.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        deliveryCount++;

        assertAll("The number of records in the repositories does not match the expected number",
                () -> assertEquals(deliveryCount, deliveryRepository.findAll().size()),
                () -> assertEquals(deliveryCount, cargoRepository.findAll().size()),
                () -> assertEquals(clientCount, clientRepository.findAll().size()),
                () -> assertEquals(addressCount, addressRepository.findAll().size())
        );

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());

        deliveryId1 = response.getId();
    }

    @Test
    @Order(4)
    void givenNotNullRequest_whenCreateSecondDelivery_thenResponseStatusCreated()
            throws Exception {
        DeliveryRequest request = TestDeliveryRequests.second();

        MvcResult result = mockMvc.perform(post(Urls.Deliveries.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        deliveryCount++;
        addressCount++;
        clientCount++;

        assertAll("The number of records in the repositories does not match the expected number",
                () -> assertEquals(deliveryCount, deliveryRepository.findAll().size()),
                () -> assertEquals(deliveryCount, cargoRepository.findAll().size()),
                () -> assertEquals(clientCount, clientRepository.findAll().size()),
                () -> assertEquals(addressCount, addressRepository.findAll().size())
        );

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    @Order(5)
    void givenNotNullIdAndRequest_whenUpdateDelivery_thenResponseStatusOk()
            throws Exception {
        DeliveryRequest request = TestDeliveryRequests.first(false);

        LocalDateTime start = LocalDateTime.now();

        MvcResult result = mockMvc.perform(put(Urls.Deliveries.FULL + "/" + deliveryId1)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertAll("The number of records in the repositories does not match the expected number",
                () -> assertEquals(deliveryCount, deliveryRepository.findAll().size()),
                () -> assertEquals(deliveryCount, cargoRepository.findAll().size()),
                () -> assertEquals(clientCount, clientRepository.findAll().size()),
                () -> assertEquals(addressCount, addressRepository.findAll().size())
        );

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
    void givenNotNullRequest_whenChangeStatusOfDelivery_thenResponseStatusOk() throws Exception {
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
    void givenNoParameters_whenGetList_thenResponseStatusOk() throws Exception {
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
    void givenNotNullId_whenGetById_thenResponseStatusOk() throws Exception {
        MvcResult result = mockMvc.perform(get(Urls.Deliveries.FULL + "/" + deliveryId1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        DeliveryResponse response = objectMapper.readValue(content, DeliveryResponse.class);

        assertNotNull(response);

        DeliveryRequest deliveryRequest1 = TestDeliveryRequests.first(false);
        assertAll("The expected values of the delivery fields do not match the values " +
                        "in the fetched object",
                () -> assertEquals(deliveryRequest1.getCargo().getWeight(),
                        response.getCargo().getWeight()),
                () -> assertEquals(deliveryRequest1.getCargo().getDeclaredValue(),
                        response.getCargo().getDeclaredValue()),
                () -> assertEquals(deliveryRequest1.getCargo().getWidth(),
                        response.getCargo().getWidth()),
                () -> assertEquals(deliveryRequest1.getCargo().getLength(),
                        response.getCargo().getLength()),
                () -> assertEquals(deliveryRequest1.getCargo().getHeight(),
                        response.getCargo().getHeight())
        );

        assertAll("The expected emails of the sender/recipient do not match the values " +
                        "in the fetched object",
                () -> assertEquals(deliveryRequest1.getSender().getEmail(),
                        response.getSender().getEmail()),
                () -> assertEquals(deliveryRequest1.getRecipient().getEmail(),
                        response.getRecipient().getEmail())
        );
    }

    @Test
    @Order(9)
    void givenNotNullStatus_whenGetFilteredListWithStatus_thenResponseStatusOk() throws Exception {
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