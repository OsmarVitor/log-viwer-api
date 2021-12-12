package com.log.viewer.service.impl;

import com.log.viewer.domain.handler.exception.EntityNotFoundException;
import com.log.viewer.domain.model.Log;
import com.log.viewer.domain.model.enums.Type;
import com.log.viewer.domain.repository.LogRepository;
import com.log.viewer.domain.repository.specification.LogSpecification;
import com.log.viewer.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements GenericService<Log> {

    @Autowired
    private LogRepository logRepository;

    @Override
    public Page<Log> findAll(int page, int size) {
        return logRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Log findById(long id) {
        return findLog(id);
    }

    @Override
    public void delete(long id) {
        Log logToDelete =findLog(id);
        logRepository.delete(logToDelete);
    }

    @Override
    public Page<Log> findByMessageLike(String message, Pageable pageable) {
        Specification messageLikeClause = LogSpecification.messageLike(message);
        return logRepository.findAll(Specification.where(messageLikeClause), pageable);
    }

    @Override
    public Page<Log> findByType(String type, Pageable pageable) {
        Specification messageLikeClause = LogSpecification.logType(Type.valueOf(type.toUpperCase()));
        return logRepository.findAll(Specification.where(messageLikeClause), pageable);
    }

    @Override
    public Page<Log> findAllWithFilter(LocalDateTime startDate, LocalDateTime endDate, String type, String message, Pageable pageable) {
        Specification typeClause = LogSpecification.logType(Type.valueOf(type.toUpperCase()));
        Specification messageLikeClause = LogSpecification.messageLike(message);
        Specification createdAtBetweenClause = LogSpecification.createdAtBetween(startDate, endDate);

        return logRepository.findAll(Specification.where(createdAtBetweenClause).and(typeClause).and(messageLikeClause), pageable);
    }


    private Log findLog(long id) {
        return logRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }
}
