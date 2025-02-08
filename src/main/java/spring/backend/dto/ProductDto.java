package spring.backend.dto;


import lombok.Getter;
import lombok.Setter;
import spring.backend.entity.OrderProduct;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private String category;
    private List<OrderProduct> orderProducts;

    public ProductDto(){}

    public ProductDto(Long productId, String name, String description, BigDecimal price,
                      Long quantity, String category, List<OrderProduct> orderProducts) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.orderProducts = orderProducts;
    }

}
