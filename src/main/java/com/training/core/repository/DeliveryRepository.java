package com.training.core.repository;

import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByStatus(DeliveryStatus status);

    Optional<Delivery> findByTrackingNumber(String trackingNumber);

}
