package spring.backend.service.implementation;

import jakarta.transaction.Transactional;
import spring.backend.dto.OrderDto;
import spring.backend.dto.ProductDto;
import spring.backend.entity.*;
import spring.backend.exception.ResourceNotFoundException;
import spring.backend.mapper.OrderMapper;
import spring.backend.mapper.ProductMapper;
import spring.backend.repository.*;
import spring.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional
    public OrderDto createOrder(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();

        Order order = new Order();
        order.setUser(user);

        // Calculate total price directly
        BigDecimal totalPrice = shoppingCart.getOrderProducts()
                .stream()
                .map(orderProduct -> orderProduct.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
        order.setStatus("Order Placed");

        // Save the order first
        order = orderRepository.save(order);

        // Create a final reference to the order
        final Order finalOrder = order;

        // Create new OrderProduct instances for the new order
        List<OrderProduct> newOrderProducts = shoppingCart.getOrderProducts()
                .stream()
                .map(orderProduct -> {
                    OrderProduct newOrderProduct = new OrderProduct();
                    newOrderProduct.setProduct(orderProduct.getProduct());
                    newOrderProduct.setQuantity(orderProduct.getQuantity());
                    newOrderProduct.setName(orderProduct.getProduct().getName());
                    newOrderProduct.setOrder(finalOrder); // Use the finalOrder reference
                    return newOrderProduct;
                })
                .collect(Collectors.toList());

        order.setOrderProducts(newOrderProducts);

        // Save all OrderProduct entities in a single batch
        orderProductRepository.saveAll(newOrderProducts);

        // Clear the shopping cart and save it
        shoppingCart.getOrderProducts().clear();
        shoppingCartRepository.save(shoppingCart);

        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Order> orders = user.getOrders();

        return orders.stream().map((order -> OrderMapper.mapToOrderDto(order)))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map((order -> OrderMapper.mapToOrderDto(order)))
                .collect(Collectors.toList());
    }

 /*   @Override
    public OrderDto updateOrder(Long orderId, OrderDto updatedOrder) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Exist With Given id: " + orderId));

        if(updatedOrder.getUserId() != null){
            order.setUser(userRepository.findById(updatedOrder.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        }

        if(updatedOrder.getOrderProducts() != null){
            List<Product> products = productRepository.findAllById(updatedOrder.getOrderProducts());

            BigDecimal totalPrice = BigDecimal.valueOf(0);
            for(Product product : products){
                totalPrice = totalPrice.add(product.getPrice());
            }

            order.setProducts(products);
            order.setTotalPrice(totalPrice);
        }

        if(updatedOrder.getStatus() != null){
            order.setStatus(updatedOrder.getStatus());
        }

        Order updatedOrderObject = orderRepository.save(order);

        return OrderMapper.mapToOrderDto(updatedOrderObject);
    }*/
    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Exist With Given id: " + orderId));

        orderRepository.deleteById(orderId);
    }
}
