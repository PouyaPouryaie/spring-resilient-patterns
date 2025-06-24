package ir.bigz.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ReviewDto {

    private String username;
    private long productId;
    private int rating;
    private String comment;

}
