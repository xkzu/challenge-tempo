package com.api.challenge.controller;

import com.api.challenge.exception.ServiceException;
import com.api.challenge.response.CalculationResponse;
import com.api.challenge.service.CalculationService;
import com.api.challenge.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculationController {

    private final CalculationService calculationService;
    private final HistoryService historyService;

    public CalculationController(CalculationService calculationService,
                                 HistoryService historyService) {
        this.calculationService = calculationService;
        this.historyService = historyService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestParam double num1, @RequestParam double num2) {
        String endpoint = "/api/calculate";
        String params = "num1=" + num1 + ", num2=" + num2;
        try {
            double result = calculationService.calculate(num1, num2);
            // Registro asíncrono de la llamada exitosa
            historyService.logAsync(endpoint, params, String.valueOf(result), null);
            return ResponseEntity.ok(new CalculationResponse(result, null));
        } catch (ServiceException e) {
            // Registro asíncrono de la llamada con error
            historyService.logAsync(endpoint, params, null, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new CalculationResponse(null, e.getMessage()));
        }
    }
}
