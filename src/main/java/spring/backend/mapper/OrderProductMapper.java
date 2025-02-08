package spring.backend.mapper;

import spring.backend.dto.OrderProductDto;
import spring.backend.dto.ProductDto;
import spring.backend.entity.OrderProduct;
import spring.backend.entity.Product;

public class OrderProductMapper {
    public static OrderProductDto mapToProductDto(OrderProduct orderProduct){
        return new OrderProductDto(
                orderProduct.getOrderProductId(),
                orderProduct.getName(),
                orderProduct.getOrder(),
                orderProduct.getShoppingCart(),
                orderProduct.getProduct(),
                orderProduct.getQuantity()
        );
    }

    public static OrderProduct mapToProduct(OrderProductDto orderProductDto){
        return new OrderProduct(
                orderProductDto.getOrderProductId(),
                orderProductDto.getName(),
                orderProductDto.getOrder(),
                orderProductDto.getShoppingCart(),
                orderProductDto.getProduct(),
                orderProductDto.getQuantity()
        );
    }
}
