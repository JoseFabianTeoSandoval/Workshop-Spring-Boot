package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.domain.model.EstadoManual;
import com.ejemplo.demo.domain.service.EstadoSingletonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo/estado")
public class EstadoComparacionController {

    private final EstadoSingletonService estadoSingletonService;

    public EstadoComparacionController(EstadoSingletonService estadoSingletonService) {
        this.estadoSingletonService = estadoSingletonService;
    }

    @PostMapping("/singleton/{valor}")
    public ResponseEntity<Map<String, Object>> actualizarSingleton(@PathVariable int valor) {
        int actual = estadoSingletonService.actualizar(valor);
        return ResponseEntity.ok(Map.of(
                "tipo", "singleton",
                "valorActual", actual
        ));
    }

    @GetMapping("/singleton")
    public ResponseEntity<Map<String, Object>> obtenerSingleton() {
        return ResponseEntity.ok(Map.of(
                "tipo", "singleton",
                "valorActual", estadoSingletonService.obtenerActual()
        ));
    }

    @PostMapping("/singleton/reset")
    public ResponseEntity<Map<String, Object>> reiniciarSingleton() {
        return ResponseEntity.ok(Map.of(
                "tipo", "singleton",
                "valorActual", estadoSingletonService.reiniciar()
        ));
    }

    @PostMapping("/manual/{valor}")
    public ResponseEntity<Map<String, Object>> actualizarManual(@PathVariable int valor) {
        EstadoManual estadoManual = new EstadoManual();
        estadoManual.setValor(valor);
        return ResponseEntity.ok(Map.of(
                "tipo", "manual",
                "valorActual", estadoManual.getValor()
        ));
    }

    @GetMapping("/manual")
    public ResponseEntity<Map<String, Object>> obtenerManual() {
        EstadoManual estadoManual = new EstadoManual();
        return ResponseEntity.ok(Map.of(
                "tipo", "manual",
                "valorActual", estadoManual.getValor()
        ));
    }
}
