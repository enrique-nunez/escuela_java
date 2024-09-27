package pe.com.enrique.nunez.escuelajava.web.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pe.com.enrique.nunez.escuelajava.service.impl.CustomerServiceImpl;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerRestControllerTest {
    @InjectMocks
    CustomerRestController customerRestController;

    @Mock
    CustomerServiceImpl customerService;

    private MockMvc mockMvc;

    @BeforeEach
    void ini() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerRestController).build();
    }

    @Test
    void shouldReturnSuccessful() throws Exception {
        when(customerService.findByCustomerProjection()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/customers/projection"))
                .andExpect(status().isOk());
    }
}
