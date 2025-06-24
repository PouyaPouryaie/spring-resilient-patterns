package ir.bigz.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Product {

    private long productId;
    private String description;
    private double price;

}
