package com.training.core.repository;

import com.training.core.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM tariff t WHERE t.effective_date = " +
                    "(SELECT MAX(q.effective_date) FROM tariff q WHERE q.effective_date <= :date)")
    Optional<Tariff> findForDate(@Param("date") LocalDate date);

}
