package com.api.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExternalPercentageServiceTest {

    @Test
    void testGetPercentage() {
        ExternalPercentageService service = new ExternalPercentageService();
        double percentage = service.getPercentage();
        assertEquals(15.0, percentage, 0.001, "El porcentaje debe ser 15.0");
    }
}
