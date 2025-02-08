package spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.backend.entity.OrderProduct;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>  {
    @Modifying
    @Query("DELETE FROM OrderProduct op WHERE op.shoppingCart.cartId = :cartId")
    void deleteAllByShoppingCartId(@Param("cartId") Long cartId);
}
