package pe.com.enrique.nunez.escuelajava.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.enrique.nunez.escuelajava.exception.ApiException;
import pe.com.enrique.nunez.escuelajava.model.projection.CustomerProjection;
import pe.com.enrique.nunez.escuelajava.model.request.CustomerRegisterRequest;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerRegisterResponse;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerResponse;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;
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

    @PostMapping("/customers")
    public ResponseEntity<CustomerRegisterResponse> register(@RequestBody CustomerRegisterRequest customerRequest ){

        if(customerRequest.getDocumentType()==null || customerRequest.getDocumentType() <= 0) {
            throw new ApiException("ERROR","Error falta declarar el numero de documentoType", HttpStatus.BAD_REQUEST);
        }


        if(customerRequest.getDocumentNumber()==null || customerRequest.getDocumentNumber().isEmpty()) {
            throw new ApiException("ERROR","Error falta declarar el documentNumber", HttpStatus.BAD_REQUEST);
        }

        CustomerRegisterResponse result = customerService.register(customerRequest);

        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<Page<Customer>> getAllCustomersToPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name= "size", defaultValue = "5") Integer size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerService.findAllCustomersToPage(pageable);

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/projection")
    public ResponseEntity<List<CustomerProjection>> getCustomer(){

        List<CustomerProjection> customers = customerService.findByCustomerProjection();

        return ResponseEntity.ok(customers);
    }
}
