package com.shopping.service;

import com.shopping.domain.dto.LoyalClientsDto;
import com.shopping.domain.dto.PurchasesDto;
import com.shopping.domain.dto.WineRecommendationDto;
import java.util.List;

public interface PurchaseService {
    List<PurchasesDto> findPurchasesInAscendingOrder();
    PurchasesDto findLargestPurchaseByYear(int year);
    List<LoyalClientsDto> findLoyalClients();
    List<WineRecommendationDto> findWineTypeByClient();
}
