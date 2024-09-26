package pe.com.enrique.nunez.escuelajava.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerResponse;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;
import pe.com.enrique.nunez.escuelajava.persistence.repository.CustomerRepository;
import pe.com.enrique.nunez.escuelajava.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> findAllCustomers() {

        List<Customer> customers =  customerRepository.findCustomers();

        List<CustomerResponse> result = customers.stream().map(customer -> {
                    return CustomerResponse.builder()
                            .customerId(customer.getCustomerId())
                            .customerAddress(customer.getCustomerAddress())
                            .customerName(customer.getCustomerName())
                            .documentType(customer.getDocumentType())
                            .build();
                })
                .collect(Collectors.toList());

        return result;
    }
}
