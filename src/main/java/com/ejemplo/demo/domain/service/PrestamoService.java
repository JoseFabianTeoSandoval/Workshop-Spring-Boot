package com.ejemplo.demo.domain.service;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.stereotype.Service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;

@Service
public class PrestamoService {
	
	public PrestamoResponse simularPrestamo(PrestamoRequest request) {

        BigDecimal P = request.monto();
        BigDecimal tasaAnual = request.tasaAnual();
        int n = request.meses();

        BigDecimal r = tasaAnual.divide(BigDecimal.valueOf(12 * 100), MathContext.DECIMAL64);

        BigDecimal unoMasR = BigDecimal.ONE.add(r);
        BigDecimal potencia = unoMasR.pow(n, MathContext.DECIMAL64);

        BigDecimal numerador = P.multiply(r.multiply(potencia));
        BigDecimal denominador = potencia.subtract(BigDecimal.ONE);

        BigDecimal cuota = numerador.divide(denominador, MathContext.DECIMAL64);

        BigDecimal totalPagar = cuota.multiply(BigDecimal.valueOf(n));
        BigDecimal interesTotal = totalPagar.subtract(P);

        return new PrestamoResponse(cuota, interesTotal, totalPagar);
    }

}
