package spring.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.backend.dto.OrderProductDto;
import spring.backend.dto.ProductDto;
import spring.backend.dto.ShoppingCartDto;
import spring.backend.entity.OrderProduct;
import spring.backend.entity.Product;
import spring.backend.entity.ShoppingCart;
import spring.backend.entity.User;
import spring.backend.exception.ResourceNotFoundException;
import spring.backend.mapper.OrderProductMapper;
import spring.backend.mapper.ProductMapper;
import spring.backend.mapper.ShoppingCartMapper;
import spring.backend.repository.OrderProductRepository;
import spring.backend.repository.ProductRepository;
import spring.backend.repository.ShoppingCartRepository;
import spring.backend.repository.UserRepository;
import spring.backend.service.ShoppingCartService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceOmplementation implements ShoppingCartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ShoppingCartDto addProductToCart(Long productId, Long quantity, Long uid) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Exist With Given id: " + productId));

        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Exist With Given id: " + uid));

        ShoppingCart shoppingCart = user.getShoppingCart();

        boolean productFound = false;

        for (OrderProduct orderProduct : shoppingCart.getOrderProducts()) {

            if (orderProduct.getProduct().getProductId().equals(productId)) {
                orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
                orderProductRepository.save(orderProduct);
                productFound = true;
                break;
            }
        }


        if (!productFound) {
            OrderProduct newOrderProduct = new OrderProduct();
            newOrderProduct.setProduct(product);
            newOrderProduct.setName(product.getName());
            newOrderProduct.setQuantity(quantity);
            newOrderProduct.setShoppingCart(shoppingCart);

            OrderProduct updatedOrderProduct = orderProductRepository.save(newOrderProduct);

            shoppingCart.addOrderProductToList(updatedOrderProduct);

            // Update product quantity only once after all OrderProduct updates
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            ShoppingCart updatedShoppingCart = shoppingCartRepository.save(shoppingCart);
            ShoppingCartDto updatedCart = ShoppingCartMapper.mapToShoppingCartDto(updatedShoppingCart);
            return updatedCart;
        }else{
            ShoppingCartDto updatedCart = ShoppingCartMapper.mapToShoppingCartDto(shoppingCart);
            return updatedCart;
        }


    }

    @Override
    public void deleteOrderProductInCart(Long orderProductId, Long uid) {
        OrderProduct orderProduct = orderProductRepository.findById(orderProductId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("OrderProduct Not Exist With Given id: " + orderProductId));

        User user = userRepository.findById(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Exist With Given id: " + uid));

        ShoppingCart shoppingcart = user.getShoppingCart();

      /*  Product product = productRepository.findById(orderProduct.getProductId())
                .orElseThrow(() ->
                new ResourceNotFoundException("OrderProduct Not Exist With Given id: " + orderProductId));*/

        Product product = orderProduct.getProduct();

        Long temp = product.getQuantity() + orderProduct.getQuantity();
        product.setQuantity(temp);

        shoppingcart.deleteOrderProductToList(orderProduct);

        Product updatedProduct = productRepository.save(product);
        ShoppingCart updatedShoppingCart = shoppingCartRepository.save(shoppingcart);
        orderProductRepository.deleteById(orderProductId);
    }

    @Override
    public List<OrderProductDto> getOrderProducts(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Exist With Given id: " + uid));

        ShoppingCart shoppingCart = user.getShoppingCart();

        List<OrderProduct> orderProducts = shoppingCart.getOrderProducts();

        return orderProducts.stream().map((orderProduct -> OrderProductMapper.mapToProductDto(orderProduct)))
                .collect(Collectors.toList());
    }

}
