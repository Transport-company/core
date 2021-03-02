package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.request.PaymentRequest;
import com.training.core.util.TestPaymentRequests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/order_data_for_payment.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PaymentControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void getList_returnPaymentPageResponse() throws Exception {
        mockMvc.perform(get(Urls.Payments.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.size").value(20))
                .andExpect(jsonPath("$.content[0].deliveryResponse.id").value(1))
                .andExpect(jsonPath("$.content[0].sum").value(120.0f));
    }

    @Test
    void getById_returnPaymentResponse() throws Exception {
        mockMvc.perform(get(Urls.Payments.FULL + "/" + 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.deliveryResponse.id").value(1))
                .andExpect(jsonPath("$.sum").value(120.0f));
    }

    @Test
    void сreatePayment_returnCreatedPaymentResponse() throws Exception {
        PaymentRequest request = TestPaymentRequests.getPaymentRequest();

        mockMvc.perform(post(Urls.Payments.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.deliveryResponse.id").value(1L))
                .andExpect(jsonPath("$.sum").value(120));
    }
}
