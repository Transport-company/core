package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.request.OrderRequest;
import com.training.core.exception.NotFoundException;
import com.training.core.service.OrderService;
import com.training.core.util.TestOrderRequests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.yml")
@Sql(value = {"/order_controller_add_data_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void givenNotNullPageable_whenGetList_thenResponseStatusOk() throws Exception {
        mockMvc.perform(get(Urls.Orders.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.size").value(20))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].cargo.weight").value(54.0f));
    }

    @Test
    void givenNotNullId_whenGetById_thenResponseStatusOk() throws Exception {
        Long id = 1L;

        mockMvc.perform(get(Urls.Orders.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cargo.weight").value(54.0f));
    }

    @Test
    void givenNotNullRequest_whenCreateOrder_thenResponseStatusCreated() throws Exception {
        OrderRequest request = TestOrderRequests.getOrderRequest();

        mockMvc.perform(post(Urls.Orders.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.cargo.weight").value(0.4f))
                .andExpect(jsonPath("$.sender.lastName").value("Ivanov"))
                .andExpect(jsonPath("$.sender.email").value("Ivanov_II@gmail.com"))
                .andExpect(jsonPath("$.shippingAddress.region").value("Nizhegorodskaya oblast"))
                .andExpect(jsonPath("$.sendingAddress.city").value("Krrasnodar"))
                .andExpect(jsonPath("$.sendingAddress.street").value("Krasnaya"));
    }

    @Test
    void givenNotNullIdAndRequest_whenUpdateOrder_thenResponseStatusOk() throws Exception {
        Long id = 3L;

        OrderRequest request = TestOrderRequests.getOrderRequest();
        OrderRequest updateRequest = TestOrderRequests.getOrderRequestForUpdate();

        mockMvc.perform(post(Urls.Orders.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        mockMvc.perform(put(Urls.Orders.FULL + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cargo.weight").value(1.2f))
                .andExpect(jsonPath("$.cargo.length").value(25f))
                .andExpect(jsonPath("$.cargo.declaredValue").value(1500.00));
    }

    @Test
    void givenNotNullId_whenDeleteOrder_thenResponseStatusOk() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete(Urls.Orders.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        assertThrows(NotFoundException.class, () -> orderService.getById(id));
    }
}
