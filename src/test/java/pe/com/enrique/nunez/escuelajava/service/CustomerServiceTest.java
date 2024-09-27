package pe.com.enrique.nunez.escuelajava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.enrique.nunez.escuelajava.exception.ApiException;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;
import pe.com.enrique.nunez.escuelajava.persistence.repository.CustomerRepository;
import pe.com.enrique.nunez.escuelajava.service.impl.CustomerServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void shouldReturnThrow() {
        Optional<Customer> customerOp = Optional.empty();
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(customerOp);

        assertThrows(ApiException.class, ()->customerService.findByCustomerId(1));

    }
}
