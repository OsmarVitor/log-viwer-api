package com.log.viewer.api.controller;

import com.log.viewer.domain.model.Log;
import com.log.viewer.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private GenericService<Log> logService;

    @GetMapping
    public ResponseEntity<Page<Log>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok(logService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> findById(@PathVariable long id) {
        Log log = logService.findById(id);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/message")
    public ResponseEntity<Page<Log>> findByMessageLike(@RequestParam(value = "message", required = true) String message,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok(logService.findByMessageLike(message, PageRequest.of(page, size, Sort.Direction.DESC, "createdAt")));
    }

    @GetMapping("/type")
    public ResponseEntity<Page<Log>> findByType(@RequestParam(value = "type", required = true) String type,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok(logService.findByType(type, PageRequest.of(page, size, Sort.Direction.DESC, "createdAt")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Log> delete(@PathVariable long id) {
        logService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("filter")
    public ResponseEntity<Page<Log>> findAllWithFilter(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
                    LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
                    LocalDateTime endDate,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "message", required = false) String message) {
        return ResponseEntity.ok(
                logService.findAllWithFilter(
                        startDate,
                        endDate,
                        type,
                        message,
                        PageRequest.of(page, size, Sort.Direction.DESC, "createdAt")));
    }
}
