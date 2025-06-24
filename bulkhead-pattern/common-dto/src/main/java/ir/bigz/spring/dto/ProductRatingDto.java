package ir.bigz.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ProductRatingDto {

    private double avgRating;
    private List<ReviewDto> reviews;
}
