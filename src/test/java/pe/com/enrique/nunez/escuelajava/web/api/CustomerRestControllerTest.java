package pe.com.enrique.nunez.escuelajava.web.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pe.com.enrique.nunez.escuelajava.service.impl.CustomerServiceImpl;

public class CustomerRestControllerTest {
    @InjectMocks
    CustomerRestController customerRestController;

    @Mock
    CustomerServiceImpl customerService;


    @BeforeEach
    void ini() {

    }


    @Test
    void shouldReturnSuccessful() {

    }
}
