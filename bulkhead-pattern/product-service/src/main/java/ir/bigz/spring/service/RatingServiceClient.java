package ir.bigz.spring.service;

import ir.bigz.spring.dto.ProductRatingDto;

public interface RatingServiceClient {

    ProductRatingDto getProductRatingDto(long productId);
}
