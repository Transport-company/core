package com.training.core.controller.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.core.Urls;
import com.training.core.dto.request.TariffRequest;
import com.training.core.dto.response.TariffPageResponse;
import com.training.core.dto.response.TariffResponse;
import com.training.core.repository.TariffRepository;
import com.training.core.util.TestTariffRequests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
@Sql(value = {"/tariff_controller_add_data_before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TariffControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TariffRepository tariffRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void givenNoParameters_whenGetList_thenResponseStatusOk() throws Exception {
        MvcResult result = mockMvc.perform(get(Urls.Tariffs.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        TariffPageResponse response = objectMapper.readValue(content, TariffPageResponse.class);

        assertNotNull(response);
        int dbSize = tariffRepository.findAll().size();
        assertEquals(dbSize, response.getTotalElements());
        List<TariffResponse> list = response.getContent();
        assertNotNull(list);
        int pageSize = response.getSize();
        assertEquals(Math.min(dbSize, pageSize), list.size());
    }

    @Test
    void givenNotNullId_whenGetById_thenResponseStatusOk() throws Exception {
        long id = 1L;

        mockMvc.perform(get(Urls.Tariffs.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.orderSum").value(100.00))
                .andExpect(jsonPath("$.courierSum").value(500.00))
                .andExpect(jsonPath("$.distancePrice").value(0.50))
                .andExpect(jsonPath("$.minDistance").value(10))
                .andExpect(jsonPath("$.distanceThreshold").value(800))
                .andExpect(jsonPath("$.reductionFactor").value(0.50))
                .andExpect(jsonPath("$.weightUnit").value(1))
                .andExpect(jsonPath("$.weightThreshold").value(1))
                .andExpect(jsonPath("$.weightRatioIncrease").value(0.1f))
                .andExpect(jsonPath("$.volumeUnit").value(0.1f))
                .andExpect(jsonPath("$.volumeThreshold").value(0.125f))
                .andExpect(jsonPath("$.volumeRatioIncrease").value(0.1f));
    }

    @Test
    void givenNotExistingId_whenGetById_thenResponseStatusNotFound() throws Exception {
        long id = 999L;

        mockMvc.perform(get(Urls.Tariffs.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void givenNotNullDate_whenGetForDate_thenResponseStatusOk() throws Exception {
        LocalDate date = LocalDate.of(2019, 1, 1);

        mockMvc.perform((get(Urls.Tariffs.Dates.FULL + "/" + date.toString())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.orderSum").value(100.00))
                .andExpect(jsonPath("$.courierSum").value(500.00))
                .andExpect(jsonPath("$.distancePrice").value(0.50))
                .andExpect(jsonPath("$.minDistance").value(10))
                .andExpect(jsonPath("$.distanceThreshold").value(800))
                .andExpect(jsonPath("$.reductionFactor").value(0.50))
                .andExpect(jsonPath("$.weightUnit").value(1))
                .andExpect(jsonPath("$.weightThreshold").value(1))
                .andExpect(jsonPath("$.weightRatioIncrease").value(0.1f))
                .andExpect(jsonPath("$.volumeUnit").value(0.1f))
                .andExpect(jsonPath("$.volumeThreshold").value(0.125f))
                .andExpect(jsonPath("$.volumeRatioIncrease").value(0.1f));
    }

    @Test
    void givenNotNullRequest_whenCreate_thenResponseStatusCreated() throws Exception {
        TariffRequest request = TestTariffRequests.from2021();
        int dbSizeBefore = tariffRepository.findAll().size();

        MvcResult result = mockMvc.perform(post(Urls.Tariffs.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertEquals(dbSizeBefore + 1, tariffRepository.findAll().size());

        String content = result.getResponse().getContentAsString();
        TariffResponse response = objectMapper.readValue(content, TariffResponse.class);

        assertNotNull(response);
        assertAll("Different field values in the request and response",
                () -> assertEquals(request.getOrderSum(), response.getOrderSum()),
                () -> assertEquals(request.getCourierSum(), response.getCourierSum()),
                () -> assertEquals(request.getDistancePrice(), response.getDistancePrice()),
                () -> assertEquals(request.getMinDistance(), response.getMinDistance()),
                () -> assertEquals(request.getDistanceThreshold(),
                        response.getDistanceThreshold()),
                () -> assertEquals(request.getReductionFactor(), response.getReductionFactor()),
                () -> assertEquals(request.getWeightUnit(), response.getWeightUnit()),
                () -> assertEquals(request.getWeightThreshold(), response.getWeightThreshold()),
                () -> assertEquals(request.getWeightRatioIncrease(),
                        response.getWeightRatioIncrease()),
                () -> assertEquals(request.getVolumeUnit(), response.getVolumeUnit()),
                () -> assertEquals(request.getVolumeThreshold(), response.getVolumeThreshold()),
                () -> assertEquals(request.getVolumeRatioIncrease(),
                        response.getVolumeRatioIncrease())
        );
    }

    @Test
    void givenNotNullRequest_whenUpdate_thenResponseStatusOk() throws Exception {
        long id = 2L;
        TariffRequest request = TestTariffRequests.from2020Update();

        MvcResult result = mockMvc.perform(put(Urls.Tariffs.FULL + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        TariffResponse response = objectMapper.readValue(content, TariffResponse.class);

        assertNotNull(response);
        assertAll("Different field values in the request and response",
                () -> assertEquals(id, response.getId()),
                () -> assertEquals(request.getOrderSum(), response.getOrderSum()),
                () -> assertEquals(request.getCourierSum(), response.getCourierSum()),
                () -> assertEquals(request.getDistancePrice(), response.getDistancePrice()),
                () -> assertEquals(request.getMinDistance(), response.getMinDistance()),
                () -> assertEquals(request.getDistanceThreshold(),
                        response.getDistanceThreshold()),
                () -> assertEquals(request.getReductionFactor(), response.getReductionFactor()),
                () -> assertEquals(request.getWeightUnit(), response.getWeightUnit()),
                () -> assertEquals(request.getWeightThreshold(), response.getWeightThreshold()),
                () -> assertEquals(request.getWeightRatioIncrease(),
                        response.getWeightRatioIncrease()),
                () -> assertEquals(request.getVolumeUnit(), response.getVolumeUnit()),
                () -> assertEquals(request.getVolumeThreshold(), response.getVolumeThreshold()),
                () -> assertEquals(request.getVolumeRatioIncrease(),
                        response.getVolumeRatioIncrease())
        );
    }

    @Test
    void givenNotNullId_whenDelete_thenResponseStatusOk() throws Exception {
        long id = 3L;
        int dbSizeBefore = tariffRepository.findAll().size();

        mockMvc.perform(delete(Urls.Tariffs.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(dbSizeBefore - 1, tariffRepository.findAll().size());
        assertTrue(tariffRepository.findById(id).isEmpty());
    }
}