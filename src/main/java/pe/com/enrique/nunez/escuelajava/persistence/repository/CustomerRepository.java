package pe.com.enrique.nunez.escuelajava.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer>{
    List<Customer> findByCustomerNameAndDocumentType(String customerName, Integer documentType);
    @Query("FROM Customer")
    List<Customer> findCustomers();
}
