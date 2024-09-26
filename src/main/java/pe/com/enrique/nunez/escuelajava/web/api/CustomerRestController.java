package pe.com.enrique.nunez.escuelajava.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerResponse;
import pe.com.enrique.nunez.escuelajava.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers/list")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){

        List<CustomerResponse> customers = customerService.findAllCustomers();

        return ResponseEntity.ok(customers);
    }
}
