package spring.backend.mapper;


import spring.backend.dto.ProductDto;
import spring.backend.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory(),
                product.getOrderProducts()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getQuantity(),
                productDto.getCategory(),
                productDto.getOrderProducts()
        );
    }
}
