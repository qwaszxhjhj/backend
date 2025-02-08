package spring.backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.backend.dto.UserDto;
import spring.backend.entity.ShoppingCart;
import spring.backend.entity.User;
import spring.backend.repository.ShoppingCartRepository;

@Component
public class UserMapper {

    @Autowired
    private static ShoppingCartRepository shoppingCartRepository;

    public static UserDto mapToUserDto(User user){
        return new UserDto(
            user.getUid(),
            user.getName(),
            user.getEmail(),
            user.getPassword(),
            user.getPhoneNumber(),
            user.getAddress(),
                user.getShoppingCart() != null ? user.getShoppingCart().getCartId() : null,
                user.getOrders()
        );
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getUid(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                userDto.getAddress(),
                null, // Initially set shoppingCart to null
                userDto.getOrders()
        );

    /*    if (userDto.getShoppingCartId() != null) {
            ShoppingCart shoppingCart = shoppingCartRepository.findById(userDto.getShoppingCartId())
                    .orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
            user.setShoppingCart(shoppingCart);
        }*/

        return user;
    }
}
