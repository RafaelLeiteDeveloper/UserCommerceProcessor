package com.shopping.adapter;

import com.shopping.port.RestTemplateUIPort;
import com.shopping.domain.dto.ClientDto;
import com.shopping.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestTemplateClientAdapter implements RestTemplateUIPort {

    private final RestTemplate restTemplate;

    @SuppressWarnings("unused")
    @Value("${spring.urls.products}")
    private String productsUrl;

    @SuppressWarnings("unused")
    @Value("${spring.urls.clients-purchases}")
    private String clientsUrl;

    @Override
    public List<ProductDto> findAllProducts() {
        ProductDto[] products = restTemplate.getForObject(productsUrl, ProductDto[].class);
        assert products != null;
        return Arrays.asList(products);
    }

    @Override
    public List<ClientDto> findAllClients() {
        ClientDto[] clients = restTemplate.getForObject(clientsUrl, ClientDto[].class);
        assert clients != null;
        return Arrays.asList(clients);
    }

}
