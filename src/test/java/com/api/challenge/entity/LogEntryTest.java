package com.api.challenge.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryTest {

    @Test
    void testPrePersistSetsTimestampIfNull() {
        LogEntry entry = new LogEntry();
        // Aseguramos que timestamp sea null antes de persistir
        entry.setTimestamp(null);
        entry.prePersist();
        assertNotNull(entry.getTimestamp(), "El timestamp deberia ser asignado automaticamente si es null");
    }

    @Test
    void testPrePersistDoesNotOverrideExistingTimestamp() {
        LocalDateTime fixedTime = LocalDateTime.of(2025, 3, 10, 12, 0);
        LogEntry entry = new LogEntry();
        entry.setTimestamp(fixedTime);
        entry.prePersist();
        assertEquals(fixedTime, entry.getTimestamp(), "El timestamp existente no deberia ser modificado");
    }
}
