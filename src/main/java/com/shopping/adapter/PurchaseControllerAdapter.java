package com.shopping.adapter;

import com.shopping.port.PurchaseUIPort;
import com.shopping.domain.dto.LoyalClientsDto;
import com.shopping.domain.dto.PurchasesDto;
import com.shopping.domain.dto.WineRecommendationDto;
import com.shopping.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PurchaseControllerAdapter implements PurchaseUIPort {

    private final PurchaseService purchaseService;

    @Override
    public ResponseEntity<List<PurchasesDto>> getPurchases() {
        List<PurchasesDto> purchasesList = purchaseService.findPurchasesInAscendingOrder();
        return ResponseEntity.status(HttpStatus.OK).body(purchasesList);
    }

    @Override
    public ResponseEntity<PurchasesDto> getLargestPurchaseByYear(@PathVariable int ano) {
        PurchasesDto purchasesDto = purchaseService.findLargestPurchaseByYear(ano);
        return ResponseEntity.status(HttpStatus.OK).body(purchasesDto);
    }

    @Override
    public ResponseEntity<List<LoyalClientsDto>> getLoyalClients(){
        List<LoyalClientsDto> loyalClients = purchaseService.findLoyalClients();
        return ResponseEntity.status(HttpStatus.OK).body(loyalClients);
    }

    @Override
    public ResponseEntity<List<WineRecommendationDto>> getWineRecommendation(){
        List<WineRecommendationDto> findWineTypeByClient = purchaseService.findWineTypeByClient();
        return ResponseEntity.status(HttpStatus.OK).body(findWineTypeByClient);
    }

}
