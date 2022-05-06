package pl.studia.ecommerence.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {
    private double price;
    private int quantity;
    private Long orderId;
    private Long productId;
}
