package ir.bigz.spring.config;

import io.github.resilience4j.core.ContextPropagator;
import ir.bigz.spring.entity.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@Slf4j
@Profile("manually")
public class RequestContextPropagator implements ContextPropagator<String> {

    @Override
    public Supplier<Optional<String>> retrieve() {
        log.info("Getting request track-Id from thread: {}", Thread.currentThread().getName());
        return () -> Optional.of(RequestContext.getRequestId());
    }

    @Override
    public Consumer<Optional<String>> copy() {
        return optional -> {
            log.info("Copy request track-Id {} on thread: {}", optional.get(), Thread.currentThread().getName());
            optional.ifPresent(RequestContext::setRequestId);
        };
    }

    @Override
    public Consumer<Optional<String>> clear() {
        return optional -> {
            log.info("Clearing request track-Id on thread: {}", Thread.currentThread().getName());
            optional.ifPresent(s -> RequestContext.clearRequestId());
        };
    }
}
