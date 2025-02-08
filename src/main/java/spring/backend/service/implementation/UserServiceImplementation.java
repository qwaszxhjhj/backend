package spring.backend.service.implementation;

import jakarta.transaction.Transactional;
import spring.backend.dto.UserDto;
import spring.backend.entity.ShoppingCart;
import spring.backend.entity.User;
import spring.backend.exception.RepeatedUniqueValueException;
import spring.backend.exception.ResourceNotFoundException;
import spring.backend.mapper.UserMapper;
import spring.backend.repository.OrderProductRepository;
import spring.backend.repository.ShoppingCartRepository;
import spring.backend.repository.UserRepository;
import spring.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user = userRepository.save(user); // Save the User entity first

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Exist With Given id: " + uid));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user -> UserMapper.mapToUserDto(user)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long uid, UserDto updatedUser) {
        User user = userRepository.findById(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Exist With Given id: " + uid));

        if(!updatedUser.getName().isEmpty()){
            user.setName(updatedUser.getName());
        }

        if(!updatedUser.getEmail().isEmpty()){
            user.setEmail(updatedUser.getEmail());
        }
        if(!updatedUser.getPassword().isEmpty()){
            user.setPassword(updatedUser.getPassword());
        }
        if(!updatedUser.getPhoneNumber().isEmpty()){
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        if(!updatedUser.getAddress().isEmpty()){
            user.setAddress(updatedUser.getAddress());
        }

        User updatedUserObject = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUserObject);
    }

    @Override
    public void deleteUser(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Exist With Given id: " + uid));

        ShoppingCart shoppingCart = user.getShoppingCart();
 //       orderProductRepository.deleteAllByShoppingCartId(shoppingCart.getCartId());
 //       shoppingCartRepository.deleteById(user.getShoppingCart().getCartId());
        userRepository.deleteById(uid);
    }
}
