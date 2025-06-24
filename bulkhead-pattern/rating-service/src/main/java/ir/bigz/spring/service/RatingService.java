package ir.bigz.spring.service;

import ir.bigz.spring.dto.ProductRatingDto;
import ir.bigz.spring.dto.ReviewDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RatingService {

    private Map<Long, ProductRatingDto> map;

    @PostConstruct
    private void init(){

        // product 1
        ProductRatingDto ratingDto1 = ProductRatingDto.of(4.5,
                List.of(
                        ReviewDto.of("alex123", 1, 5, "awesome"),
                        ReviewDto.of("pouya321", 1, 4, "best")
                )
        );

        // product 2
        ProductRatingDto ratingDto2 = ProductRatingDto.of(3,
                List.of(
                        ReviewDto.of("tim456", 2, 4, "decent"),
                        ReviewDto.of("susan654", 2, 2, "not bad")
                )
        );

        this.map = Map.of(
                1L, ratingDto1,
                2L, ratingDto2
        );

    }

    public ProductRatingDto getRatingForProduct(long productId) {
        return this.map.getOrDefault(productId, new ProductRatingDto());
    }
}
