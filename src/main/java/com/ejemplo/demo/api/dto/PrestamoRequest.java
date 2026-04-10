package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrestamoRequest(
		
		@JsonProperty("monto")
	    @NotNull(message = "El monto es obligatorio")
	    @DecimalMin(value = "0.0", inclusive = false)
	    BigDecimal monto,

	    @JsonProperty("tasaAnual")
	    @NotNull(message = "La tasa es obligatoria")
	    @DecimalMin(value = "0.0", inclusive = false)
	    BigDecimal tasaAnual,

	    @JsonProperty("meses")
	    @NotNull(message = "Los meses son obligatorios")
	    @Min(1)
	    @Max(360)
	    Integer meses
        ) {}
