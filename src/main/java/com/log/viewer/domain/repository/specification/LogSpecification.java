package com.log.viewer.domain.repository.specification;

import com.log.viewer.domain.model.Log;
import com.log.viewer.domain.model.enums.Type;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;

public class LogSpecification {

    public static Specification<Log> createdAtBetween(
            LocalDateTime startedAt, LocalDateTime endedAt) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                Objects.isNull(startedAt)
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.between(root.get("createdAt"), startedAt, endedAt));
    }

    public static Specification<Log> logType(Type type) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                type == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(
                        root.get("type"), type));
    }

    public static Specification<Log> messageLike (String message) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                Objects.isNull(message)
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.get("message"), "%" + message + "%"));
    }

}
