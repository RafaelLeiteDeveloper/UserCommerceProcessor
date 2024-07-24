package com.shopping.port;

import com.shopping.domain.dto.LoyalClientsDto;
import com.shopping.domain.dto.PurchasesDto;
import com.shopping.domain.dto.WineRecommendationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@SuppressWarnings("unused")
public interface PurchaseUIPort {

    @GetMapping
    ResponseEntity<List<PurchasesDto>> getPurchases();

    @GetMapping("/maior-compra/{ano}")
    ResponseEntity<PurchasesDto> getLargestPurchaseByYear(@PathVariable int year);

    @GetMapping("/clientes-fieis")
    ResponseEntity<List<LoyalClientsDto>> getLoyalClients();

    @GetMapping("/recomendacao/cliente/tipo")
    ResponseEntity<List<WineRecommendationDto>> getWineRecommendation();

}
