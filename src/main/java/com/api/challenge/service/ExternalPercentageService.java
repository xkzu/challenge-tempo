package com.api.challenge.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalPercentageService {

    @Cacheable("percentageCache")
    public double getPercentage() {
        // Mock para obtener el porcentaje de un servicio externo
        return 15.0;
    }
}
