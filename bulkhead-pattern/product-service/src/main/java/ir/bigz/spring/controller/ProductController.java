package ir.bigz.spring.controller;
import ir.bigz.spring.dto.ProductDto;
import ir.bigz.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable int productId){
        return this.productService.getProductDto(productId);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() throws InterruptedException {
        Thread.sleep(50);
        return this.productService.getAllProducts();
    }
}
