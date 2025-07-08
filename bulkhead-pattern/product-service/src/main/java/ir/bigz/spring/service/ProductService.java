package ir.bigz.spring.service;

import ir.bigz.spring.dto.ProductDto;
import ir.bigz.spring.dto.ProductRatingDto;
import ir.bigz.spring.entity.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RatingServiceClient ratingServiceClient;
    private Map<Long, Product> map;

    @PostConstruct
    private void init(){
        this.map = Map.of(
                1L, Product.of(1L, "Magic Mouse", 30.99),
                2L, Product.of(2L, "Ghost Keyboard", 45.49)
        );
    }

    public ProductDto getProductDto(long productId){
        ProductRatingDto ratingDto = this.ratingServiceClient.getProductRatingDto(1);
        Product product = this.map.get(productId);
        return ProductDto.of(productId, product.getPrice(), product.getDescription(), ratingDto);
    }

    public List<ProductDto> getAllProducts(){
        return this.map.values()
                .stream()
                .map(product -> ProductDto.of(product.getProductId(), product.getPrice(),
                        product.getDescription(), ProductRatingDto.of(0, Collections.emptyList())))
                .collect(Collectors.toList());
    }
}
