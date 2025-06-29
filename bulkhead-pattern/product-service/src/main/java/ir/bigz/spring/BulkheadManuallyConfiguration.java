package ir.bigz.spring;

import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
@Profile("manually")
public class BulkheadManuallyConfiguration {

    @Bean
    public ThreadPoolBulkheadConfig getBulkheadConfig() {
        return ThreadPoolBulkheadConfig.custom().maxThreadPoolSize(10).coreThreadPoolSize(1).queueCapacity(1).build();
    }

    @Bean
    public ThreadPoolBulkheadRegistry getBulkheadRegistry(ThreadPoolBulkheadConfig bulkheadConfig) {
        return ThreadPoolBulkheadRegistry.of(bulkheadConfig);
    }

    @Bean
    public ThreadPoolBulkhead getBulkhead(ThreadPoolBulkheadRegistry bulkheadRegistry) {
        return bulkheadRegistry.bulkhead("ratingServiceManually");
    }
}
