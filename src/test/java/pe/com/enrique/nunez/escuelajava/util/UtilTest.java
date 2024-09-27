package pe.com.enrique.nunez.escuelajava.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    private UtilTest() {}

    public static Customer getCustomersToList(){

        Resource resource = new ClassPathResource("customer.json");

        ObjectMapper objectMapper = new ObjectMapper();
        Customer result = null;
        // Leer el archivo JSON y deserializarlo en un objeto Product
        try {
            result = objectMapper.readValue(resource.getInputStream(), Customer.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static Iterable<Product> getProducts(){
        Product product = Product.builder()
                .productId(1)
                .productName("test")
                .productPrice(BigDecimal.valueOf(10.00))
                .status(true)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        // cast
        Iterable<Product> productIterable = productList;

        return productIterable;
    }
}
