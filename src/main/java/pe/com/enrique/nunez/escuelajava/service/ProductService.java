package pe.com.enrique.nunez.escuelajava.service;

import pe.com.enrique.nunez.escuelajava.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllProducts();
}
