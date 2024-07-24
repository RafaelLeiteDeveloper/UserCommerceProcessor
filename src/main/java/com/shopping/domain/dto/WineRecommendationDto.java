package com.shopping.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WineRecommendationDto {
    private String nome;
    private String cpf;
    private String recomendacao_vinho;

    public static WineRecommendationDto buildWineRecommendationDto(String name,
                                                                   String cpf,
                                                                   String mostFrequentWine){
        return WineRecommendationDto.builder()
                .nome(name)
                .cpf(cpf)
                .recomendacao_vinho(mostFrequentWine)
                .build();
    }
}
