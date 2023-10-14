package br.inatel.labs.labjpa.dto;

import java.math.BigDecimal;

public record TotalCompradoPorFornecedor(String fornecedorRazaoSocial, BigDecimal totalComprado) {
    
}
