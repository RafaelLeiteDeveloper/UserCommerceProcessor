package com.shopping.service.impl;

import com.shopping.domain.dto.*;
import com.shopping.port.RestTemplateUIPort;
import com.shopping.service.PurchaseService;
import com.shopping.util.UtilsList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final RestTemplateUIPort restTemplateUIPort;

    @Override
    public List<PurchasesDto> findPurchasesInAscendingOrder() {
        List<ClientDto> clients = this.getAllClients();
        List<ProductDto> products = this.getAllProducts();
        List<PurchasesDto> allPurchases = this.findAllPurchases(clients, products);
        return UtilsList.sortListByHighestPrice(allPurchases);
    }

    @Override
    public PurchasesDto findLargestPurchaseByYear(int year) {
        List<ClientDto> clients = this.getAllClients();
        List<ProductDto> products = this.getAllProducts();
        List<PurchasesDto> allPurchases = this.findAllPurchases(clients, products);
        return UtilsList.sortListByHighestPriceByYear(allPurchases, year);
    }

    @Override
    public List<LoyalClientsDto> findLoyalClients() {
        List<ClientDto> clients = this.getAllClients();
        List<ProductDto> products = this.getAllProducts();
        return this.getLoyalCustomers(clients, products);
    }

    @Override
    public List<WineRecommendationDto> findWineTypeByClient() {
        List<ClientDto> clients = this.getAllClients();
        List<ProductDto> products = this.getAllProducts();
        return this.findWineRecommendationByClient(clients, products);
    }

    private List<ClientDto> getAllClients() {
        return restTemplateUIPort.findAllClients();
    }

    private List<ProductDto> getAllProducts() {
        return restTemplateUIPort.findAllProducts();
    }


    private List<PurchasesDto> findAllPurchases(
            List<ClientDto> clients,
            List<ProductDto> products) {

        List<PurchasesDto> purchasesList = new ArrayList<>();

        clients.forEach(client ->
                client.getCompras()
                      .forEach(purchase -> this.addProductToList(purchasesList, products, purchase, client))
        );
        return purchasesList;
    }

    private List<WineRecommendationDto> findWineRecommendationByClient(
            List<ClientDto> clients,
            List<ProductDto> products) {

        List<WineRecommendationDto> wineRecommendation = new ArrayList<>();

        clients.forEach(client -> {
            List<String> wineTypes = new ArrayList<>();
            client.getCompras()
                  .forEach(purchase -> this.populateWineTypesPurchasedByUser(products, purchase, wineTypes));

            String mostFrequentWine = wineTypes
                    .stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElseThrow(() -> new RuntimeException("Unexpected error: No most frequent wine found"));

            wineRecommendation
                    .add(WineRecommendationDto
                            .buildWineRecommendationDto(client.getNome(), client.getCpf(), mostFrequentWine));
        });

        return wineRecommendation;
    }

    public List<LoyalClientsDto> getLoyalCustomers(
            List<ClientDto> clients,
            List<ProductDto> products) {

        Map<ClientDto, Integer> sumQuantitiesByClient = this.sumQuantitiesByClient(clients);
        List<Map.Entry<ClientDto, Integer>> topClients = this.getTopClients(sumQuantitiesByClient);
        Map<ClientDto, Double> totalSpendingByClient = this.totalSpendingByClient(topClients, products);
        List<Map.Entry<ClientDto, Double>> topClientsBySpending = this.topClientsBySpending(totalSpendingByClient);
        return this.listMapToList(topClientsBySpending);
    }


    private Map<ClientDto, Integer> sumQuantitiesByClient(List<ClientDto> clients){
        return clients.stream()
                     .collect(Collectors.toMap(
                              client -> client,
                              client -> client.getCompras()
                                              .stream()
                                             .mapToInt(PurchasedProductsDto::getQuantidade)
                                             .sum()
                ));

    }

    private List<Map.Entry<ClientDto, Integer>> getTopClients(Map<ClientDto, Integer> sumQuantitiesByClient){
        return sumQuantitiesByClient
                .entrySet()
                .stream()
                .sorted(Map.Entry.<ClientDto, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
    }


    private Map<ClientDto, Double> totalSpendingByClient(List<Map.Entry<ClientDto, Integer>> topClients,
                                                          List<ProductDto> products) {
        return topClients
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getKey()
                                .getCompras()
                                .stream()
                                .mapToDouble(purchase -> {
                                    ProductDto product = ProductDto.findProductByCode(products, purchase.getCodigo());
                                    return purchase.getQuantidade() * product.getPreco();
                                })
                                .sum()
                ));
    }

    private  List<Map.Entry<ClientDto, Double>> topClientsBySpending(
            Map<ClientDto, Double> totalSpendingByClient){

        return totalSpendingByClient
                .entrySet()
                .stream()
                .sorted(Map.Entry.<ClientDto, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    private List<LoyalClientsDto> listMapToList(List<Map.Entry<ClientDto, Double>> topClientsBySpending){
        return topClientsBySpending.stream()
                .map(entry ->
                        LoyalClientsDto.builder()
                                .cpf(entry.getKey().getCpf())
                                .nome(entry.getKey().getNome())
                                .gastos(entry.getValue())
                                .build())
                .collect(Collectors.toList());
    }


    private void populateWineTypesPurchasedByUser(
            List<ProductDto> products,
            PurchasedProductsDto purchase,
            List<String> wineTypes) {

        ProductDto purchasedProduct = ProductDto.findProductByCode(products, purchase.getCodigo());
        for (int i = 0; i < purchase.getQuantidade(); i++) {
            wineTypes.add(purchasedProduct.getTipo_vinho());
        }
    }


    private void addProductToList(
            List<PurchasesDto> purchasesList,
            List<ProductDto> products,
            PurchasedProductsDto purchase,
            ClientDto client) {

        ProductDto productDto = ProductDto.findProductByCode(products, purchase.getCodigo());
        purchasesList.add(PurchasesDto.gerarCompra(client, productDto, purchase));
    }

}
