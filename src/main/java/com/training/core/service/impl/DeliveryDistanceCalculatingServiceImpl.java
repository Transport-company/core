package com.training.core.service.impl;

import com.training.core.model.Delivery;
import com.training.core.service.DeliveryDistanceCalculatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryDistanceCalculatingServiceImpl implements DeliveryDistanceCalculatingService {

    @Override
    public int getDistance(Delivery delivery) {
        log.info("Calculating the distance between {} {}",
                String.format("%s %s",
                        delivery.getSendingAddress().getRegion(),
                        delivery.getSendingAddress().getCity()),
                String.format("%s %s",
                        delivery.getShippingAddress().getRegion(),
                        delivery.getShippingAddress().getCity()));
        int min = 1;
        int max = 5000;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

}
