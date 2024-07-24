package com.shopping.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchasesDto {
    private String nome;
    private String cpf;
    private ProductDto dadosProduto;
    private int quantidade;
    private double preco;


    public static PurchasesDto gerarCompra(ClientDto clientDto,
                                           ProductDto productDto,
                                           PurchasedProductsDto purchasedProductsDto){
        return PurchasesDto.builder()
                .cpf(clientDto.getCpf())
                .nome(clientDto.getNome())
                .dadosProduto(productDto)
                .quantidade(purchasedProductsDto.getQuantidade())
                .preco(productDto.getPreco() * purchasedProductsDto.getQuantidade())
                .build();
    }
}
