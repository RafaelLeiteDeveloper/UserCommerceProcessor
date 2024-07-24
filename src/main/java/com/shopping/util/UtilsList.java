package com.shopping.util;

import com.shopping.domain.dto.PurchasesDto;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UtilsList {

    public static List<PurchasesDto> sortListByHighestPrice(List<PurchasesDto> purchases){
        return purchases.stream()
                      .sorted(Comparator.comparingDouble(PurchasesDto::getPreco).reversed())
                      .collect(Collectors.toList());
    }

    public static PurchasesDto sortListByHighestPriceByYear(List<PurchasesDto> purchases, int year){
        return purchases.stream()
                      .filter( purchase -> purchase.getDadosProduto().getAno_compra() == year)
                      .max(Comparator.comparingDouble(PurchasesDto::getPreco))
                      .orElseThrow(() -> new NoSuchElementException("Purchase not found this year"));
    }
}
