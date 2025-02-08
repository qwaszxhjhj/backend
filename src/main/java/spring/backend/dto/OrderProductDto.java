package spring.backend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.backend.entity.Order;
import spring.backend.entity.Product;
import spring.backend.entity.ShoppingCart;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDto {
    private Long orderProductId;
    private String name;
    private Order order;
    private ShoppingCart shoppingCart;
    private Product product;
    private Long quantity;
}
