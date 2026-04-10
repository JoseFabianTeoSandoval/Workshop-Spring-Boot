package com.ejemplo.demo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.demo.api.dto.SaludoRequest;
import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.domain.service.SaludoService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SaludoController {

    @GetMapping
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "estado", "ok",
                "mensaje", "Workshop Spring Boot activo"
        ));
    }

    /*
    ===========================================
    PASO 2: DESCOMENTA este bloque y prueba GET
    ===========================================

    1) Descomenta imports:
       - com.ejemplo.demo.api.dto.SaludoResponse
       - com.ejemplo.demo.domain.service.SaludoService
       - org.springframework.web.bind.annotation.RequestParam

    2) Descomenta el campo y constructor:
    

    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    3) Descomenta este endpoint:

    @GetMapping("/saludos")
    public ResponseEntity<SaludoResponse> saludar(
            @RequestParam(defaultValue = "Mundo") String nombre
    ) {
        return ResponseEntity.ok(saludoService.crearSaludo(nombre));
    }
    */
    
    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @GetMapping("/saludos")
    public ResponseEntity<SaludoResponse> saludar(
            @RequestParam(defaultValue = "Mundo") String nombre
    ) {
        return ResponseEntity.ok(saludoService.crearSaludo(nombre));
    }
    
    @PostMapping("/saludos")
    public ResponseEntity<SaludoResponse> saludarPost(@Valid @RequestBody SaludoRequest request) {
        return ResponseEntity.ok(saludoService.crearSaludo(request.nombre()));
    }

    /*
    ============================================
    PASO 3: DESCOMENTA este bloque y prueba POST
    ============================================

    1) Descomenta imports:
       - com.ejemplo.demo.api.dto.SaludoRequest
       - jakarta.validation.Valid
       - org.springframework.web.bind.annotation.PostMapping
       - org.springframework.web.bind.annotation.RequestBody

    2) Descomenta este endpoint:

    @PostMapping("/saludos")
    public ResponseEntity<SaludoResponse> saludarPost(@Valid @RequestBody SaludoRequest request) {
        return ResponseEntity.ok(saludoService.crearSaludo(request.nombre()));
    }
    */
}
