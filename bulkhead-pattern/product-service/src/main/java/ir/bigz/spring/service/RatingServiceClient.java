package ir.bigz.spring.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import ir.bigz.spring.dto.ProductRatingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RatingServiceClient {

    private final RestClient restClient;

    @Bulkhead(name = "ratingService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "getDefault")
    public ProductRatingDto getProductRatingDto(int productId){
        return restClient.get().uri("/{productId}", productId).retrieve().body(ProductRatingDto.class);
    }

    public ProductRatingDto getDefault(int productId, Throwable throwable){
        return ProductRatingDto.of(0, Collections.emptyList());
    }
}
