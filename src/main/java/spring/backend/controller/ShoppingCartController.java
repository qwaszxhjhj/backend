package spring.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.backend.dto.OrderProductDto;
import spring.backend.dto.ProductDto;
import spring.backend.dto.ShoppingCartDto;
import spring.backend.service.ShoppingCartService;
import spring.backend.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    // Build Post Shopping REST API
    @PostMapping("/add/{productId}/{quantity}/{userId}")
    public ResponseEntity<ShoppingCartDto> addProductToCart(
            @PathVariable("productId") Long productId,
            @PathVariable("quantity") Long quantity,
            @PathVariable("userId") Long userId) {
        ShoppingCartDto cartDto =  shoppingCartService.addProductToCart(productId, quantity, userId);
        return ResponseEntity.ok(cartDto);
    }


    // Build Get All Products REST API
    @GetMapping("/orderproducts/{userId}")
    public ResponseEntity<List<OrderProductDto>> getOrderProducts(@PathVariable("userId") Long userId){
        List<OrderProductDto> orderProducts = shoppingCartService.getOrderProducts(userId);
        return ResponseEntity.ok(orderProducts);
    }

    // Build Delete Order Products REST API
    @DeleteMapping("/orderproducts/delete/{orderProductId}/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable("orderProductId") Long orderProductId, @PathVariable("uid") Long uid) {
        shoppingCartService.deleteOrderProductInCart(orderProductId, uid);
        return ResponseEntity.ok().body("{\"message\": \"Order Product Deleted Successful\"}");
    }
}
