package pl.studia.ecommerence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studia.ecommerence.model.Order;
import pl.studia.ecommerence.model.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
