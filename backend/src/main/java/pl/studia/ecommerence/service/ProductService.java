package pl.studia.ecommerence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.studia.ecommerence.exception.ApiRequestException;
import pl.studia.ecommerence.model.Product;
import pl.studia.ecommerence.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> productList(Optional<Integer> page) {
        return productRepository.findAll(PageRequest.of(page.orElse(0), 8));
    }

    public Product getProduct(Long id) throws ApiRequestException {
        if (!productRepository.existsById(id)) {
            throw new ApiRequestException("Item with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }
        return productRepository.getById(id);
    }

    public List<Product> allProductsList() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ApiRequestException("Product with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }

        productRepository.deleteById(id);
    }

    public void update(Long id, Product productDto) {
        if (!productRepository.existsById(id)) {
            throw new ApiRequestException("Product with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.getById(id);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageURL(productDto.getImageURL());
        productRepository.save(product);
    }
}
