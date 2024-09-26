package pe.com.enrique.nunez.escuelajava.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.enrique.nunez.escuelajava.exception.ApiException;
import pe.com.enrique.nunez.escuelajava.mapper.CustomerMapper;
import pe.com.enrique.nunez.escuelajava.model.projection.CustomerProjection;
import pe.com.enrique.nunez.escuelajava.model.request.CustomerRegisterRequest;
import pe.com.enrique.nunez.escuelajava.model.response.CustomerRegisterResponse;
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

    @Override
    public CustomerResponse findByCustomerId(Integer customerId) {

        Optional<Customer> customerOp = customerRepository.findByCustomerId(customerId);


        //Optional<Customer> customerOp =  customerRepository.findById(customerId);
        if(customerOp.isEmpty()) {
            throw new ApiException("ERROR","No existe customerId: "+customerId, HttpStatus.NOT_FOUND);
        }

        Customer customer = customerOp.get();
        CustomerResponse result = CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .customerAddress(customer.getCustomerAddress())
                .customerName(customer.getCustomerName())
                .documentType(customer.getDocumentType())
                .build();


        return result;
    }


    @Override
    public Page<Customer> findAllCustomersToPage(Pageable pageable) {

        Page<Customer> customers =  customerRepository.findAll(pageable);

        return customers;
    }

    @Override
    public List<CustomerProjection> findByCustomerProjection() {

        List<CustomerProjection> customers = customerRepository.findCustomerProjection();


        return customers;
    }

    @Override
    @Transactional
    public CustomerRegisterResponse register(CustomerRegisterRequest customerRequest) {
        Customer customer = CustomerMapper.INSTANCE.customerToCustomerRequest(customerRequest);
        Customer customerResult = null;
        try {
            customerResult = customerRepository.save(customer);
        }catch(IllegalArgumentException e) {
            throw new ApiException("ERROR","Error al insertar un Customer", HttpStatus.NOT_FOUND);
        }

        // Conversion de entity a response
        CustomerRegisterResponse result = CustomerRegisterResponse.builder()
                .customerId(customerResult.getCustomerId())
                .customerName(customerResult.getCustomerName())
                .documentType(customerResult.getDocumentType())
                .customerAddress(customerResult.getCustomerAddress())
                .documentNumber(customerResult.getDocumentNumber())
                .build();

        return result;
    }
}
