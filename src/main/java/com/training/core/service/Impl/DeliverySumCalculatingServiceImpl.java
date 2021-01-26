package com.training.core.service.Impl;

import com.training.core.service.DeliverySumCalculatingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DeliverySumCalculatingServiceImpl implements DeliverySumCalculatingService {

    @Override
    public BigDecimal getSum() {
        return new BigDecimal(Math.random());
    }
}
