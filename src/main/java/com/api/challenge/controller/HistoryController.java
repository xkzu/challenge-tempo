package com.api.challenge.controller;

import com.api.challenge.entity.LogEntry;
import com.api.challenge.repository.LogEntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HistoryController {

    private final LogEntryRepository logEntryRepository;

    public HistoryController(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    @GetMapping("/history")
    public Page<LogEntry> getHistory(Pageable pageable) {
        return logEntryRepository.findAll(pageable);
    }
}
