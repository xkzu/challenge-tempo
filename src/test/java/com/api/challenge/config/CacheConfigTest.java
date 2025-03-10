package com.api.challenge.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.test.util.ReflectionTestUtils;

class CacheConfigUnitTest {

    @Test
    void testCacheManagerCreation() {
        // Creamos manualmente la clase de configuración
        CacheConfig cacheConfig = new CacheConfig();

        // Forzamos la inyección de la propiedad 'ttlMinutes'
        ReflectionTestUtils.setField(cacheConfig, "ttlMinutes", 5L);

        // Llamamos al metodo que crea el bean CacheManager
        CacheManager cacheManager = cacheConfig.cacheManager();

        // Verificamos que se haya creado correctamente
        assertNotNull(cacheManager, "CacheManager no debe ser null");
    }
}
