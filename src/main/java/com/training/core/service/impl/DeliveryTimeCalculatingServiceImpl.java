package com.training.core.service.impl;

import com.training.core.service.DeliveryTimeCalculatingService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTimeCalculatingServiceImpl implements DeliveryTimeCalculatingService {

    @Override
    public Long getTime() {
        return (long) Math.random();
    }
}
