package pe.com.enrique.nunez.escuelajava.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.enrique.nunez.escuelajava.model.response.ProductResponse;
import pe.com.enrique.nunez.escuelajava.persistence.repository.ProductRepository;
import pe.com.enrique.nunez.escuelajava.service.impl.ProductServiceImpl;
import pe.com.enrique.nunez.escuelajava.util.UtilTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void init() {
        log.info("Se ejecuta primero");
    }

    @Test
    void shouldReturnList() {
        log.info(UtilTest.getCustomersToList().getCustomerId().toString());

        // simulacion de findAll
        when(productRepository.findAll()).thenReturn(UtilTest.getProducts());
        List<ProductResponse> result = productService.findAllProducts();
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        //assertEquals(false,result.get(0).getStatus());
    }
}
