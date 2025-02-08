package spring.backend.service;

import spring.backend.dto.OrderProductDto;
import spring.backend.dto.ProductDto;
import spring.backend.dto.ShoppingCartDto;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartDto addProductToCart(Long productId, Long quantity, Long uid);

    void deleteOrderProductInCart(Long orderProductId, Long uid);

    List<OrderProductDto> getOrderProducts(Long uid);
}
