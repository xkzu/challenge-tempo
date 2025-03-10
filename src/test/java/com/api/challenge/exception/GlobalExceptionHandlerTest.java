package com.api.challenge.exception;

import com.api.challenge.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleServiceException() {
        ServiceException ex = new ServiceException("Service failed", new RuntimeException("error"));

        ResponseEntity<ErrorResponse> response = handler.handleServiceException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), body.getStatus());
        assertEquals("Service failed", body.getMessage());
    }

    @Test
    void testHandleGlobalException() {
        Exception ex = new Exception("General error");

        ResponseEntity<ErrorResponse> response = handler.handleGlobalException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.getStatus());
        assertEquals("General error", body.getMessage());
    }
}
