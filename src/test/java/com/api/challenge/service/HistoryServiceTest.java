package com.api.challenge.service;

import com.api.challenge.entity.LogEntry;
import com.api.challenge.repository.LogEntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.timeout;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @Mock
    private LogEntryRepository logEntryRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    void testLogAsync() {
        String endpoint = "/api/calculate";
        String parameters = "num1=50.0, num2=20.0";
        String response = "80.5";
        String errorMessage = null;

        historyService.logAsync(endpoint, parameters, response, errorMessage);

        // Dado que el metodo es asincrono, se usa un timeout para verificar la llamada
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(logEntryRepository, timeout(1000)).save(captor.capture());

        LogEntry capturedEntry = captor.getValue();
        assertEquals(endpoint, capturedEntry.getEndpoint());
        assertEquals(parameters, capturedEntry.getParameters());
        assertEquals(response, capturedEntry.getResponse());
        assertNull(capturedEntry.getErrorMessage());
    }
}
