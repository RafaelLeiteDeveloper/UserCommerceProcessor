package com.shopping.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.adapter.fixture.PurchaseControllerAdapterFixture;
import com.shopping.config.TestConfig;
import com.shopping.service.PurchaseService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("unused")
@Import({PurchaseControllerAdapter.class})
@WebMvcTest({PurchaseControllerAdapter.class})
@ContextConfiguration(classes = TestConfig.class)
public class PurchaseControllerAdapterTest {

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mvc;

    @MockBean
    @SuppressWarnings("unused")
    private PurchaseService purchaseService;

    private static ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void calledGetPurchases() throws Exception {

        when(purchaseService.findPurchasesInAscendingOrder())
                            .thenReturn(PurchaseControllerAdapterFixture.getListPurchase());

        var result = mvc.perform(get("/"))
                        .andExpect(status().isOk())
                        .andReturn();

        verify(purchaseService, times(1)).findPurchasesInAscendingOrder();
        assertEquals(this.toJson(PurchaseControllerAdapterFixture.getListPurchase()), result.getResponse().getContentAsString());

    }

    @Test
    void calledGetLargestPurchaseByYear() throws Exception {

        when(purchaseService.findLargestPurchaseByYear(2020))
                .thenReturn(PurchaseControllerAdapterFixture.getPurchase());

        var result = mvc.perform(get("/maior-compra/2020"))
                .andExpect(status().isOk())
                .andReturn();

        verify(purchaseService, times(1)).findLargestPurchaseByYear(anyInt());
        assertEquals(this.toJson(PurchaseControllerAdapterFixture.getPurchase()), result.getResponse().getContentAsString());

    }


    @Test
    void calledGetLoyalClients() throws Exception {

        when(purchaseService.findLoyalClients())
                .thenReturn(PurchaseControllerAdapterFixture.getListLoyalClientsDto());

        var result = mvc.perform(get("/clientes-fieis"))
                .andExpect(status().isOk())
                .andReturn();

        verify(purchaseService, times(1)).findLoyalClients();
        assertEquals(this.toJson(PurchaseControllerAdapterFixture.getListLoyalClientsDto()), result.getResponse().getContentAsString());

    }


    @Test
    void calledGetWineRecommendation() throws Exception {

        when(purchaseService.findWineTypeByClient())
                .thenReturn(PurchaseControllerAdapterFixture.getListWineRecommendationDto());

        var result = mvc.perform(get("/recomendacao/cliente/tipo"))
                .andExpect(status().isOk())
                .andReturn();

        verify(purchaseService, times(1)).findWineTypeByClient();
        assertEquals(this.toJson(PurchaseControllerAdapterFixture.getListWineRecommendationDto()), result.getResponse().getContentAsString());

    }

    @SneakyThrows(JsonProcessingException.class)
    private String toJson(Object dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
