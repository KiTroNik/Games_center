package pl.studia.ecommerence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studia.ecommerence.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}