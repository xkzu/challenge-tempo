package com.api.challenge.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        ErrorResponse errorResponse = new ErrorResponse(500, "Internal Server Error");
        assertEquals(500, errorResponse.getStatus());
        assertEquals("Internal Server Error", errorResponse.getMessage());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(404);
        errorResponse.setMessage("Not Found");
        assertEquals(404, errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getMessage());
    }

    @Test
    void testToString() {
        ErrorResponse errorResponse = new ErrorResponse(500, "Error occurred");
        String s = errorResponse.toString();
        assertNotNull(s);
        assertTrue(s.contains("500"));
        assertTrue(s.contains("Error occurred"));
    }
}
