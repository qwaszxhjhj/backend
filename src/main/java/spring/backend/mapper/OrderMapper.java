package spring.backend.mapper;

import spring.backend.dto.OrderDto;
import spring.backend.dto.ProductDto;
import spring.backend.entity.Order;
import spring.backend.entity.OrderProduct;
import spring.backend.entity.Product;
import spring.backend.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {
    public static OrderDto mapToOrderDto(Order order){
        return new OrderDto(
                order.getOrderId(),
                order.getUser(),
                order.getOrderProducts(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }

    public static Order mapToOrder(OrderDto orderDto, User user, List<OrderProduct> orderProducts, BigDecimal totalPrice){
        return new Order(
                orderDto.getOrderId(),
                user,
                orderProducts,
                totalPrice,
                orderDto.getStatus()
        );
    }
}
