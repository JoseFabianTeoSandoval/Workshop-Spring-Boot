package com.ejemplo.demo.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.domain.service.SaludoService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    @Test
    @DisplayName("GET /saludos debe responder correctamente")
    void deberiaRetornarSaludo() throws Exception {
        when(saludoService.crearSaludo("Ana"))
                .thenReturn(new SaludoResponse("Hola Ana", Instant.now()));

        mockMvc.perform(get("/api/v1/saludos")
                .param("nombre", "Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    @DisplayName("POST /saludos valido debe responder correctamente")
    void deberiaCrearSaludo() throws Exception {
        when(saludoService.crearSaludo("Ana"))
                .thenReturn(new SaludoResponse("Hola Ana", Instant.now()));

        String json = "{\"nombre\":\"Ana\"}";

        mockMvc.perform(post("/api/v1/saludos")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    @DisplayName("POST /saludos invalido debe retornar 400")
    void deberiaRetornarErrorValidacion() throws Exception {
        String json = "{\"nombre\":\"\"}";

        mockMvc.perform(post("/api/v1/saludos")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }

    /*
    PASO 6 (EJERCICIO):
    Cuando habilites los endpoints de /api/v1/saludos, crea estas pruebas:

    1) GET /api/v1/saludos?nombre=Ana -> 200 y mensaje correcto
    2) POST /api/v1/saludos con {"nombre":""} -> 400 y codigo VALIDATION_ERROR
    */
}
