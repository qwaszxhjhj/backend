package spring.backend.service.implementation;

import spring.backend.dto.ProductDto;
import spring.backend.entity.Product;
import spring.backend.exception.ResourceNotFoundException;
import spring.backend.mapper.ProductMapper;
import spring.backend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.backend.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Exist With Given id: " + productId));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
 //       System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQ");
        return products.stream().map((product -> ProductMapper.mapToProductDto(product)))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Exist With Given id: " + productId));

        if(updatedProduct.getName() != null){
            product.setName(updatedProduct.getName());
        }
        if(updatedProduct.getDescription() != null){
            product.setDescription(updatedProduct.getDescription());
        }
        if(updatedProduct.getPrice() != null){
            product.setPrice(updatedProduct.getPrice());
        }
        if(updatedProduct.getQuantity() != null){
            product.setQuantity(updatedProduct.getQuantity());
        }
        if(updatedProduct.getCategory() != null){
            product.setCategory(updatedProduct.getCategory());
        }

        Product updatedProductObject = productRepository.save(product);

        return ProductMapper.mapToProductDto(updatedProductObject);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Exist With Given id: " + productId));

        productRepository.deleteById(productId);
    }

}
