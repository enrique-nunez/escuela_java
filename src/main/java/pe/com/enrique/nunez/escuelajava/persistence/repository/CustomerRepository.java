package pe.com.enrique.nunez.escuelajava.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.com.enrique.nunez.escuelajava.model.projection.CustomerProjection;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer>{
    List<Customer> findByCustomerNameAndDocumentType(String customerName, Integer documentType);
    @Query("FROM Customer")
    List<Customer> findCustomers();
    Optional<Customer> findByCustomerId(Integer customerId);
    @Query("SELECT c.customerId as customerId, c.customerName as customerName FROM Customer c" )
    List<CustomerProjection> findCustomerProjection();
}
