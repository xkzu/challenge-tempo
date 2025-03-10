package com.api.challenge.service;

import com.api.challenge.entity.LogEntry;
import com.api.challenge.repository.LogEntryRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private final LogEntryRepository logEntryRepository;

    public HistoryService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    @Async("asyncExecutor")
    public void logAsync(String endpoint, String parameters, String response, String errorMessage) {
        LogEntry entry = new LogEntry();
        entry.setEndpoint(endpoint);
        entry.setParameters(parameters);
        entry.setResponse(response);
        entry.setErrorMessage(errorMessage);

        logEntryRepository.save(entry);
    }
}
