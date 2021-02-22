package com.training.core.service.impl;

import com.training.core.service.DeliverySumCalculatingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DeliverySumCalculatingServiceImpl implements DeliverySumCalculatingService {

    @Override
    public BigDecimal getSum(int distance, LocalDate date) {
        return new BigDecimal(Math.random());
    }
}
