package spring.backend.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.backend.entity.Order;
import spring.backend.entity.ShoppingCart;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long uid;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Long shoppingCartId;
    private List<Order> orders;

    public UserDto(){}

    public UserDto(Long uid, String name, String email, String password, String phoneNumber,
                   String address, Long shoppingCartId, List<Order> orders) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.shoppingCartId = shoppingCartId;
        this.orders = orders;
    }

}
