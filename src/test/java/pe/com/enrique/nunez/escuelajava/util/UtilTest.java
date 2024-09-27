package pe.com.enrique.nunez.escuelajava.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;

import java.io.IOException;

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
}
