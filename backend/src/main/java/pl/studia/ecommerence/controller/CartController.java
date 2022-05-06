package pl.studia.ecommerence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.studia.ecommerence.dto.cart.AddToCartDto;
import pl.studia.ecommerence.dto.cart.CartDto;
import pl.studia.ecommerence.dto.cart.UpdateCartDto;
import pl.studia.ecommerence.model.Product;
import pl.studia.ecommerence.model.User;
import pl.studia.ecommerence.service.CartService;
import pl.studia.ecommerence.service.ProductService;
import pl.studia.ecommerence.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> addToCart(@RequestBody AddToCartDto addToCartDto) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring("Bearer ".length());
        User user = userService.getUserFromToken(token);
        Product product = productService.getProduct(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartDto> getCartItems() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring("Bearer ".length());
        User user = userService.getUserFromToken(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<Object> updateCartItem(@RequestBody @Valid UpdateCartDto cartDto, @PathVariable Long cartItemId) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring("Bearer ".length());
        User user = userService.getUserFromToken(token);
        cartService.updateCartItem(cartDto, cartItemId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Object> deleteCartItem(@PathVariable Long cartItemId) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring("Bearer ".length());
        User user = userService.getUserFromToken(token);
        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
