package spring.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import spring.backend.repository.OrderProductRepository;
import spring.backend.service.OrderProductService;

public class OrderProductServiceImplementation implements OrderProductService {
    @Autowired
    private OrderProductRepository productRepository;

    /*@Override
    public OrderProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapToProductDto(savedProduct);
    }*/
}
