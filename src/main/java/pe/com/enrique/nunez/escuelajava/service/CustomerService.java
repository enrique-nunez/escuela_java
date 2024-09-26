package pe.com.enrique.nunez.escuelajava.service;

import pe.com.enrique.nunez.escuelajava.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAllCustomers();
}
