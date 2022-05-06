package pl.studia.ecommerence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studia.ecommerence.model.Cart;
import pl.studia.ecommerence.model.Product;
import pl.studia.ecommerence.model.User;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
    Optional<Cart> findByProductAndUser(Product product, User user);
    List<Cart> deleteByUser(User user);
}
