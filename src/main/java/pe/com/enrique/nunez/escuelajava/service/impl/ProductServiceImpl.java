package pe.com.enrique.nunez.escuelajava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.enrique.nunez.escuelajava.model.response.ProductResponse;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Product;
import pe.com.enrique.nunez.escuelajava.persistence.repository.ProductRepository;
import pe.com.enrique.nunez.escuelajava.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> findAllProducts() {

        Iterable<Product> products = productRepository.findAll();
        List<ProductResponse> productList = StreamSupport.stream(products.spliterator(), false)
                .map(p -> {
                    return ProductResponse.builder()
                            .productId(p.getProductId())
                            .productName(p.getProductName())
                            .productPrice(p.getProductPrice())
                            .status(p.getStatus())
                            .build();
                })
                .collect(Collectors.toList());

        return productList;
    }
}
