package pe.com.enrique.nunez.escuelajava.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import pe.com.enrique.nunez.escuelajava.persistence.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
