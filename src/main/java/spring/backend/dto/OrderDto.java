package spring.backend.dto;

import lombok.Getter;
import lombok.Setter;
import spring.backend.entity.OrderProduct;
import spring.backend.entity.User;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class OrderDto {
    private Long orderId;
    private User user;
    private List<OrderProduct> orderProducts;
    private BigDecimal totalPrice;
    private String status;

    public OrderDto() {}

    public OrderDto(Long orderId, User user, List<OrderProduct> orderProducts, BigDecimal totalPrice, String status) {
        this.orderId = orderId;
        this.user = user;
        this.orderProducts = orderProducts;
        this.totalPrice = totalPrice;
        this.status = status;
    }

}
