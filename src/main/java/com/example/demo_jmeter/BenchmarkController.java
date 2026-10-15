package com.example.demo_jmeter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/benchmark")
public class BenchmarkController {

    // ALGORITMO INEFICIENTE: dos bucles anidados -> O(n^2)
    @GetMapping("/ineficiente")
    public String procesamientoIneficiente(@RequestParam(defaultValue = "2000") int n) {
        long inicio = System.nanoTime();

        long acumulador = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                acumulador += (i + j) % 2;
            }
        }

        long fin = System.nanoTime();
        double ms = (fin - inicio) / 1_000_000.0;
        return "Ineficiente O(n^2) para n=" + n + " completado en: " + ms
                + " ms. Resultado: " + acumulador;
    }

    // ALGORITMO OPTIMIZADO: un solo bucle -> O(n)
    @GetMapping("/optimizado")
    public String procesamientoOptimizado(@RequestParam(defaultValue = "2000") int n) {
        long inicio = System.nanoTime();

        long[] c = new long[3];
        for (int i = 0; i < n; i++) {
            c[i % 3]++;
        }

        long acumulador = 0;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                acumulador += c[a] * c[b] * ((a + b) % 2);
            }
        }

        long fin = System.nanoTime();
        double ms = (fin - inicio) / 1_000_000.0;
        return "Optimizado O(n) para n=" + n + " completado en: " + ms
                + " ms. Resultado: " + acumulador;
    }
}