package com.shopping.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String nome;
    private String cpf;
    private List<PurchasedProductsDto> compras;
}
