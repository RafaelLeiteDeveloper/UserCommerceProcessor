package com.shopping.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int codigo;
    private String tipo_vinho;
    private double preco;
    private String safra;
    private int ano_compra;

    public static ProductDto findProductByCode(List<ProductDto> products, String code){
        return products.stream()
                       .filter(product -> product.getCodigo() == Integer.parseInt(code))
                       .findFirst()
                       .orElseThrow(() -> new NoSuchElementException("Product with code " + code + " not found"));
    }

}
