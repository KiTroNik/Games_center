package pl.studia.ecommerence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.studia.ecommerence.model.Product;
import pl.studia.ecommerence.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> listProducts(@RequestParam Optional<Integer> page) {
        return productService.productList(page);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/all")
    public List<Product> listAllProducts() {
        return productService.allProductsList();
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody Product productDto) {
        productService.save(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        productService.update(productId, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
