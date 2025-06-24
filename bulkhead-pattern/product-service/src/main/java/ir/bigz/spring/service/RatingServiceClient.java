package ir.bigz.spring.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import ir.bigz.spring.dto.ProductRatingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServiceClient {

    private final RestClient restClient;

//    @Bulkhead(name = "ratingService", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "getRatingFallback")
    public ProductRatingDto getProductRatingDto(long productId){
        return restClient.get().uri("/{productId}", productId).retrieve().body(ProductRatingDto.class);
    }

    public ProductRatingDto getRatingFallback(long productId, BulkheadFullException e){
        log.info("Get default rating for product with id {}, message {}", productId, e.getMessage());
        return ProductRatingDto.of(0, Collections.emptyList());
    }

    public ProductRatingDto getRatingFallback(long productId, Exception e) {
        log.error("An unexpected error occurred during service call for product: {}. Exception: {}", productId, e.getMessage());
        return ProductRatingDto.of(0, Collections.emptyList());
    }
}
