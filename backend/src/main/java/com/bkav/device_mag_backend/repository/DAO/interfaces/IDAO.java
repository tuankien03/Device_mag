package com.bkav.device_mag_backend.repository.DAO.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

public interface IDAO<T, ID> {

    @Transactional
    T save(T t);

    @Transactional
    void delete(T t);

    @Transactional(readOnly = true)
    T findById(ID id);

    @Transactional(readOnly = true)
    List<T> findAll();

    // Additional methods as needed
}