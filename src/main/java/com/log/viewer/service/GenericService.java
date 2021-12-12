package com.log.viewer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface GenericService<T> {

    Page<T> findAll(int page, int size);

    T findById(long id);

    void delete(long id);

    Page<T> findByMessageLike(String message, Pageable pageable);

    Page<T> findByType(String type, Pageable pageable);

    Page<T> findAllWithFilter(LocalDateTime startDate, LocalDateTime endDate, String type, String message, Pageable pageable);
}
