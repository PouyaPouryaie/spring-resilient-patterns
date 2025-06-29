package ir.bigz.spring.config;

import ir.bigz.spring.entity.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Slf4j
@Component
@Profile("manually")
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(Objects.isNull(request.getHeader("X-UUID"))) {
            throw new RuntimeException("Client not allowed");
        }

        RequestContext.setRequestId(request.getHeader("X-UUID"));

        log.info("Request start for URI: {}, X-UUID: {}, on thread: {}",
                request.getRequestURI(), RequestContext.getRequestId(), Thread.currentThread().getName());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Request End for URI: {}, X-UUID: {}, status: {}",
                request.getRequestURI(), RequestContext.getRequestId(), response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Clear the context once the request is complete
        log.info("afterCompletion tracking id: {}, on thread: {}", RequestContext.getRequestId(), Thread.currentThread().getName());
        RequestContext.clearRequestId();
    }
}
