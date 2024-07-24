package com.shopping.port;

import com.shopping.domain.dto.ClientDto;
import com.shopping.domain.dto.ProductDto;
import java.util.List;

public interface RestTemplateUIPort {
    List<ProductDto> findAllProducts();
    List<ClientDto> findAllClients();
}
