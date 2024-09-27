package pe.com.enrique.nunez.escuelajava.web.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pe.com.enrique.nunez.escuelajava.model.response.ProductResponse;
import pe.com.enrique.nunez.escuelajava.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl service;

    @Test
    @DisplayName("Test_para_retornar_que_mi_endpoint_este_bien")
    void shouldReturnSuccessful() throws Exception {

        ProductResponse productResponse = ProductResponse.builder()
                .productId(1)
                .productName("test")
                .productPrice(BigDecimal.valueOf(100.00))
                .status(true)
                .build();

        List<ProductResponse> products = List.of(productResponse);


        when(service.findAllProducts()).thenReturn(products);
        this.mockMvc.perform(get("/api/v1/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1));


    }
}
