package com.api.challenge.service;

import com.api.challenge.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @Mock
    private ExternalPercentageService externalPercentageService;

    @InjectMocks
    private CalculationService calculationService;

    @Test
    void testCalculateSuccess() {
        double num1 = 50.0;
        double num2 = 20.0;

        when(externalPercentageService.getPercentage()).thenReturn(15.0);

        double result = calculationService.calculate(num1, num2);
        assertEquals(80.5, result, 0.001, "El calculo debe ser 80.5");
    }

    @Test
    void testCalculateFailure() {
        double num1 = 50.0;
        double num2 = 20.0;

        when(externalPercentageService.getPercentage())
                .thenThrow(new ServiceException("Servicio externo fallido", new RuntimeException("Fallo")));

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            calculationService.calculate(num1, num2);
        });
        assertEquals("No se pudo obtener el porcentaje", exception.getMessage());
    }
}
