package pl.studia.ecommerence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studia.ecommerence.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
