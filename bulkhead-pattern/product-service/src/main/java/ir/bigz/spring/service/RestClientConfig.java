package ir.bigz.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${rating.service.endpoint}")
    private String ratingService;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl(ratingService).build();
    }
}
