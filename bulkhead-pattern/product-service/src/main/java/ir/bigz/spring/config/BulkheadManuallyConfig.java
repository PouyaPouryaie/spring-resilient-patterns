package ir.bigz.spring.config;

import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
@Profile("manually")
public class BulkheadManuallyConfig {

    @Bean
    public ThreadPoolBulkheadConfig getBulkheadConfig() {
        return ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(10)
                .coreThreadPoolSize(1)
                .queueCapacity(1)
                .contextPropagator(new RequestContextPropagator())
                .build();
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
