package ir.bigz.spring.service;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import ir.bigz.spring.dto.ProductRatingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("manually")
public class BulkheadManuallyRatingServiceClient implements RatingServiceClient {

    private final ThreadPoolBulkhead threadPoolBulkhead;
    private final RestClient restClient;

    public ProductRatingDto getProductRatingDto(long productId) {
        Supplier<CompletionStage<ProductRatingDto>> completionStageSupplier = ThreadPoolBulkhead
                .decorateSupplier(threadPoolBulkhead, () -> restClient.get().uri("/{productId}", productId)
                        .retrieve().body(ProductRatingDto.class));

        try {
            return completionStageSupplier.get().toCompletableFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Internal Server Error for product with id {}, Error: {}", productId, e.getMessage());
            return ProductRatingDto.of(0, Collections.emptyList());
        } catch (BulkheadFullException e) {
            return getRatingFallback(productId, e);
        }
    }

    private ProductRatingDto getRatingFallback(long productId, Exception e) {
        log.info("Fallback: Get default rating for product with id {}, message {}", productId, e.getMessage());
        return ProductRatingDto.of(0, Collections.emptyList());
    }
}
