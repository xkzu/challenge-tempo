package com.api.challenge.controller;

import com.api.challenge.exception.ServiceException;
import com.api.challenge.service.CalculationService;
import com.api.challenge.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculationController.class)
class CalculationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationService calculationService;

    @MockBean
    private HistoryService historyService;

    @Test
    void testCalculateSuccess() throws Exception {
        double num1 = 50.0;
        double num2 = 20.0;
        double result = 80.5;

        // Simulamos que el servicio calcula correctamente
        when(calculationService.calculate(num1, num2)).thenReturn(result);

        mockMvc.perform(post("/api/calculate")
                        .param("num1", String.valueOf(num1))
                        .param("num2", String.valueOf(num2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(result)))
                .andExpect(jsonPath("$.errorMessage", is(nullValue())));
    }

    @Test
    void testCalculateFailure() throws Exception {
        double num1 = 50.0;
        double num2 = 20.0;
        String errorMessage = "No se pudo obtener el porcentaje";

        // Simulamos que el servicio lanza una excepcion
        when(calculationService.calculate(num1, num2))
                .thenThrow(new ServiceException(errorMessage, new RuntimeException("error")));

        mockMvc.perform(post("/api/calculate")
                        .param("num1", String.valueOf(num1))
                        .param("num2", String.valueOf(num2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.result", is(nullValue())))
                .andExpect(jsonPath("$.errorMessage", is(errorMessage)));
    }
}
