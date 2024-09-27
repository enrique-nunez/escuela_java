package pe.com.enrique.nunez.escuelajava.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.enrique.nunez.escuelajava.model.response.ProductResponse;
import pe.com.enrique.nunez.escuelajava.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService=productService;
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(){

        List<ProductResponse> response = productService.findAllProducts();

        return ResponseEntity.ok(response);

    }

}
