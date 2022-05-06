package pl.studia.ecommerence.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddToCartDto {
    private Long id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;
}
