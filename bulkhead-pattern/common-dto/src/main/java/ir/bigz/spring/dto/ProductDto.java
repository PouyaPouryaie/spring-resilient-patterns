package ir.bigz.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ProductDto {

    private long productId;
    private double price;
    private String description;
    private ProductRatingDto productRating;
}
