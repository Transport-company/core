package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.request.AddressRequest;
import com.training.core.dto.request.CargoRequest;
import com.training.core.dto.request.ClientRequest;
import com.training.core.dto.request.OrderRequest;
import com.training.core.dto.response.OrderPageResponse;
import com.training.core.dto.response.OrderResponse;
import com.training.core.model.*;
import com.training.core.service.OrderService;
import com.training.core.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.yml")
@Sql(value = {"/add-data-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-data-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrderControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderControllerImpl orderController;

    @Autowired
    private OrderServiceImpl orderService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    @Test
    void getList() throws Exception{
        MvcResult result =  mockMvc.perform(get(Urls.Orders.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        OrderPageResponse response = objectMapper.readValue(content, OrderPageResponse.class);

        assertNotNull(response);
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getTotalElements());

        List<OrderResponse> list = response.getContent();
        assertNotNull(list);
        assertEquals(1, list.size());
    }


    @Test
    void getById() throws Exception {
        this.mockMvc.perform(get(Urls.Orders.FULL + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void create() throws Exception{
        OrderRequest request = getOrderRequest();

        this.mockMvc.perform(post(Urls.Orders.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.cargo.weight").value(1.0f))
                .andExpect(jsonPath("$.sender.lastName").value("Sidorov"))
                .andExpect(jsonPath("$.sender.email").value("Sidorov@gmail.com"))
                .andExpect(jsonPath("$.shippingAddress.region").value("Ulyanovsk region"))
                .andExpect(jsonPath("$.sendingAddress.street").value("Goncharova"))
                .andExpect(jsonPath("$.sendingAddress.city").value("Ulyanovsk"))
                .andReturn();
    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private OrderRequest getOrderRequest(){
        CargoRequest cargoRequest = CargoRequest.builder()
                .declaredValue(new BigDecimal(10))
                .weight(1f)
                .length(1f)
                .width(1f)
                .height(1f)
                .build();

        ClientRequest senderRequest = ClientRequest.builder()
                .lastName("Sidorov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthday(LocalDate.of(2020, 11, 11))
                .phoneNumber("89356398565")
                .email("Sidorov@gmail.com")
                .build();

        ClientRequest recipientRequest = ClientRequest.builder()
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthday(LocalDate.of(2020, 12, 12))
                .phoneNumber("99999999999")
                .email("Ivan@gmail.com")
                .build();

        AddressRequest sendingAddress = AddressRequest.builder()
                .region("Ulyanovsk region")
                .city("Ulyanovsk")
                .street("Goncharova")
                .house("1")
                .apartment("1")
                .build();

        AddressRequest shippingAddress = AddressRequest.builder()
                .region("Ulyanovsk region")
                .city("Ulyanovsk")
                .street("Lenina")
                .house("1")
                .apartment("1")
                .build();

        return OrderRequest.builder()
                .enabledNotifications(true)
                .cargo(cargoRequest)
                .sender(senderRequest)
                .recipient(recipientRequest)
                .sendingAddress(sendingAddress)
                .shippingAddress(shippingAddress)
                .build();
    }
}
