package pl.studia.ecommerence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.studia.ecommerence.dto.cart.CartDto;
import pl.studia.ecommerence.dto.cart.CartItemDto;
import pl.studia.ecommerence.dto.order.PlaceOrderDto;
import pl.studia.ecommerence.dto.order.UpdateOrderDto;
import pl.studia.ecommerence.exception.ApiRequestException;
import pl.studia.ecommerence.model.Order;
import pl.studia.ecommerence.model.OrderItem;
import pl.studia.ecommerence.model.User;
import pl.studia.ecommerence.repository.OrderItemRepository;
import pl.studia.ecommerence.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(CartService cartService, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cartService = cartService;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public void placeOrder(User user, PlaceOrderDto orderDto) {
        CartDto cartDto = cartService.listCartItems(user);
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        newOrder.setPhoneNumber(orderDto.getPhoneNumber());
        newOrder.setCity(orderDto.getCity());
        newOrder.setPostalCode(orderDto.getPostalCode());
        newOrder.setStreet(orderDto.getStreet());
        newOrder.setStatus("Waiting for payment.");
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            orderItemRepository.save(orderItem);
        }
        cartService.deleteUserCartItems(user);
    }

    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) throws ApiRequestException {
        if (!orderRepository.existsById(id)) {
            throw new ApiRequestException("Item with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }

        return orderRepository.getById(id);
    }

    public void updateOrder(Long id, UpdateOrderDto orderDto) {
        if (!orderRepository.existsById(id)) {
            throw new ApiRequestException("Order with this id doesn't exists.", HttpStatus.NOT_FOUND);
        }

        Order order = orderRepository.getById(id);
        order.setStatus(orderDto.getStatus());
        orderRepository.save(order);
    }
}
