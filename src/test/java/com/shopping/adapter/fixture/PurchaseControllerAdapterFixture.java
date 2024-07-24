package com.shopping.adapter.fixture;

import com.shopping.domain.dto.LoyalClientsDto;
import com.shopping.domain.dto.ProductDto;
import com.shopping.domain.dto.PurchasesDto;
import com.shopping.domain.dto.WineRecommendationDto;

import java.util.List;

public class PurchaseControllerAdapterFixture {

    public static List<PurchasesDto> getListPurchase() {

        ProductDto product1 = new ProductDto(101, "Cabernet Sauvignon", 59.99, "2018", 2020);
        ProductDto product2 = new ProductDto(102, "Merlot", 39.99, "2019", 2021);

        return List.of(
                new PurchasesDto("Joao Silva", "123.456.789-00", product1, 3, 179.97),
                new PurchasesDto("Maria Oliveira", "987.654.321-00", product2, 5, 199.95)
        );
    }

    public static PurchasesDto getPurchase() {
        ProductDto product = new ProductDto(101, "Cabernet Sauvignon", 59.99, "2018", 2020);
        return new PurchasesDto("Joao Silva", "123.456.789-00", product, 3, 179.97);
    }

    public static List<LoyalClientsDto> getListLoyalClientsDto() {
        return List.of(
                LoyalClientsDto.builder()
                        .nome("Joao Silva")
                        .cpf("123.456.789-00")
                        .gastos(1200.50)
                        .build()
                ,
                LoyalClientsDto.builder()
                        .nome("Maria Oliveira")
                        .cpf("987.654.321-00")
                        .gastos(1500.75)
                        .build()
                );
    }

    public static List<WineRecommendationDto> getListWineRecommendationDto() {

        return List.of(new WineRecommendationDto("Joao Silva", "123.456.789-00", "Cabernet Sauvignon"),
                       new WineRecommendationDto("Maria Oliveira", "987.654.321-00", "Merlot")
        );
    }
}
