package pl.studia.ecommerence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.studia.ecommerence.dto.cart.AddToCartDto;
import pl.studia.ecommerence.dto.cart.CartDto;
import pl.studia.ecommerence.dto.cart.CartItemDto;
import pl.studia.ecommerence.dto.cart.UpdateCartDto;
import pl.studia.ecommerence.exception.ApiRequestException;
import pl.studia.ecommerence.model.Cart;
import pl.studia.ecommerence.model.Product;
import pl.studia.ecommerence.model.User;
import pl.studia.ecommerence.repository.CartRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) throws ApiRequestException {
        if (cartRepository.findByProductAndUser(product, user).isPresent()) {
            throw new ApiRequestException("This item is already added to cart for this user.", HttpStatus.BAD_REQUEST);
        }
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto:cartItems) {
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems, totalCost);
    }

    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }

    public void updateCartItem(UpdateCartDto cartDto, Long id, User user) {
        if (!cartRepository.existsById(id)) {
            throw new ApiRequestException("Cart with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }

        Cart cart = cartRepository.getById(id);
        if (cart.getUser() != user) {
            throw new ApiRequestException("This cart item doesn't belong to you.", HttpStatus.FORBIDDEN);
        }

        cart.setQuantity(cartDto.getQuantity());
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long id, User user) throws ApiRequestException {
        if (!cartRepository.existsById(id)) {
            throw new ApiRequestException("Cart with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }

        Cart cart = cartRepository.getById(id);
        if (cart.getUser() != user) {
            throw new ApiRequestException("This cart item doesn't belong to you.", HttpStatus.FORBIDDEN);
        }

        cartRepository.deleteById(id);
    }

    public void deleteCartItems(Long userId) {
        cartRepository.deleteAll();
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}
