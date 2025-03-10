package com.api.challenge.service;

import com.api.challenge.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    private final ExternalPercentageService externalPercentageService;

    public CalculationService(ExternalPercentageService externalPercentageService) {
        this.externalPercentageService = externalPercentageService;
    }

    public double calculate(double num1, double num2) {
        double percentage;
        try {
            // Intenta obtener el porcentaje del servicio externo
            percentage = externalPercentageService.getPercentage();
        } catch (ServiceException e) {
            throw new ServiceException("No se pudo obtener el porcentaje", e);
        }
        return (num1 + num2) * (1 + (percentage / 100));
    }
}
