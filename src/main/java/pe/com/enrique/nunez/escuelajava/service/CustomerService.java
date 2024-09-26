package pe.com.enrique.nunez.escuelajava.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.enrique.nunez.escuelajava.model.projection.CustomerProjection;
import pe.com.enrique.nunez.escuelajava.model.request.CustomerRegisterRequest;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerRegisterResponse;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerResponse;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAllCustomers();
    CustomerResponse findByCustomerId(Integer customerId);
    Page<Customer> findAllCustomersToPage(Pageable pageRequest);
    List<CustomerProjection> findByCustomerProjection();
    CustomerRegisterResponse register(CustomerRegisterRequest customerRequest);
}
