package spring.backend.service;

import spring.backend.dto.OrderDto;
import spring.backend.dto.ProductDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(Long uid);

    List<OrderDto> getOrdersByUserId(Long uid);

    List<OrderDto> getAllOrders();

  //  OrderDto updateOrder(Long orderId, OrderDto updatedOrder);

    void deleteOrder(Long orderId);
}
