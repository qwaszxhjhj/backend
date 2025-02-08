package spring.backend.mapper;

import spring.backend.dto.ProductDto;
import spring.backend.dto.ShoppingCartDto;
import spring.backend.entity.Product;
import spring.backend.entity.ShoppingCart;

public class ShoppingCartMapper {
    public static ShoppingCartDto mapToShoppingCartDto(ShoppingCart shoppingCart){
        return new ShoppingCartDto(
                shoppingCart.getCartId(),
                shoppingCart.getUser(),
                shoppingCart.getOrderProducts()
        );
    }

    public static ShoppingCart mapToShoppingCart(ShoppingCartDto shoppingCartDto){
        return new ShoppingCart(
                shoppingCartDto.getCartId(),
                shoppingCartDto.getUser(),
                shoppingCartDto.getOrderProducts()
        );
    }
}
