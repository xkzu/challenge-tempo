package com.api.challenge.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class AsyncConfigTest {

    @Test
    void testAsyncExecutorBean() throws ExecutionException, InterruptedException {
        // Carga el contexto con la configuración AsyncConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);

        // Obtiene el bean "asyncExecutor" de tipo Executor
        Executor executor = context.getBean("asyncExecutor", Executor.class);

        // Verifica que el bean no sea nulo
        assertNotNull(executor, "El bean asyncExecutor debe estar definido");

        // Verifica que el bean sea una instancia de ThreadPoolTaskExecutor
        assertTrue(executor instanceof ThreadPoolTaskExecutor, "El bean debe ser una instancia de ThreadPoolTaskExecutor");
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) executor;

        // Verifica el tamaño mínimo y máximo del pool
        assertEquals(2, taskExecutor.getCorePoolSize(), "El core pool size debe ser 2");
        assertEquals(5, taskExecutor.getMaxPoolSize(), "El max pool size debe ser 5");

        // Envía una tarea simple que retorne el nombre del hilo
        Future<String> future = taskExecutor.submit(() -> Thread.currentThread().getName());
        String threadName = future.get();
        assertTrue(threadName.startsWith("AsyncExecutor-"), "El prefijo del nombre del hilo debe comenzar con 'AsyncExecutor-'");

        context.close();
    }
}
