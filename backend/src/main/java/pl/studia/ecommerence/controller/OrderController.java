package pl.studia.ecommerence.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.studia.ecommerence.dto.order.PlaceOrderDto;
import pl.studia.ecommerence.dto.order.UpdateOrderDto;
import pl.studia.ecommerence.model.Order;
import pl.studia.ecommerence.model.User;
import pl.studia.ecommerence.service.OrderService;
import pl.studia.ecommerence.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody PlaceOrderDto orderDto) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring("Bearer ".length());
        User user = userService.getUserFromToken(token);
        orderService.placeOrder(user, orderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring("Bearer ".length());
        User user = userService.getUserFromToken(token);
        List<Order> orderDtoList = orderService.listOrders(user);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Order> listAllOrders() {
        return orderService.allOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@RequestBody @Valid UpdateOrderDto orderDto, @PathVariable Long orderId) {
        orderService.updateOrder(orderId, orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
