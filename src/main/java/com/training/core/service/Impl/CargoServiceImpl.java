package com.training.core.service.Impl;

import com.training.core.exception.ErrorMessages;
import com.training.core.exception.NotFoundException;
import com.training.core.model.Cargo;
import com.training.core.repository.CargoRepository;
import com.training.core.service.CargoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Slf4j
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {
    private final CargoRepository cargoRepository;

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Cargo getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the cargo with id: {}", id);
        return cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the cargo with id " + id));
    }

    @Transactional
    @NonNull
    @Override
    public Cargo save(@NonNull Cargo cargo) {
        Assert.notNull(cargo, ErrorMessages.NULL_CARGO_OBJECT.getErrorMessage());

        Cargo saved = cargoRepository.save(cargo);
        log.info("Saved a new cargo with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public Cargo update(@NonNull Long id, @NonNull Cargo cargo) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(cargo, ErrorMessages.NULL_CARGO_OBJECT.getErrorMessage());

        Cargo fetched = getById(id);
        cargo.setId(fetched.getId());
        cargo.setCreated(fetched.getCreated());
        Cargo updated = cargoRepository.save(cargo);
        log.info("Updated the client with id: {}", updated.getId());
        return updated;
    }

}
