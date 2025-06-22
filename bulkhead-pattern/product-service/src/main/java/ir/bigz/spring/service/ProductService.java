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
    private Map<Integer, Product> map;

    @PostConstruct
    private void init(){
        this.map = Map.of(
                1, Product.of(1, "Blood On The Dance Floor", 12.45),
                2, Product.of(2, "The Eminem Show", 12.12)
        );
    }

    public ProductDto getProductDto(int productId){
        ProductRatingDto ratingDto = this.ratingServiceClient.getProductRatingDto(1);
        Product product = this.map.get(productId);
        return ProductDto.of(productId, product.getDescription(), product.getPrice(), ratingDto);
    }

    public List<ProductDto> getAllProducts(){
        return this.map.values()
                .stream()
                .map(product -> ProductDto.of(product.getProductId(), product.getDescription(), product.getPrice(), ProductRatingDto.of(0, Collections.emptyList())))
                .collect(Collectors.toList());
    }
}
