package spring.backend.controller;
import spring.backend.dto.OrderDto;
import spring.backend.dto.ProductDto;
import spring.backend.service.OrderService;
import spring.backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Build Post Order REST API
    @PostMapping("/createOrder/{uid}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody Long uid){
        OrderDto savedOrder = orderService.createOrder(uid);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Build Get Order REST API
    @GetMapping("/find/{uid}")
    public ResponseEntity<List<OrderDto>> getOrder(@PathVariable("uid") Long uid) {
        List<OrderDto> orderDtos = orderService.getOrdersByUserId(uid);
        return ResponseEntity.ok(orderDtos);
    }

    // Build Get All Orders REST API
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Build Put Order REST API
/*    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long orderId, @RequestBody OrderDto updatedOrder){
        OrderDto orderDto = orderService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(orderDto);
    }*/

    // Build Delete Order REST API
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().body("{\"message\": \"Order Deleted Successful\"}");
    }
}
