package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.response.DeliveryResponse;
import com.training.core.dto.response.OrderPageResponse;
import com.training.core.dto.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.is;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


    @Test
    void getList() throws Exception{
    }


    @Test
    void getById() throws Exception {
        this.mockMvc.perform(get(Urls.Orders.FULL + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void create(){

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
