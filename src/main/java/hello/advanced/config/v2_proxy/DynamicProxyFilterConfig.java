package hello.advanced.config.v2_proxy;

import hello.advanced.app.v1.*;
import hello.advanced.config.v2_proxy.handler.LogTraceBasicHandler;
import hello.advanced.config.v2_proxy.handler.LogTraceFilterHandler;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    public static final String[] PATTERNS = {"request*", "order*", "save*"};
    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderController = new
                OrderControllerV1Impl(orderServiceV1(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1)
                Proxy.newProxyInstance(DynamicProxyFilterConfig.class.getClassLoader(),
                        new Class[]{OrderControllerV1.class},
                        new LogTraceFilterHandler(orderController, logTrace, PATTERNS)
                );
        return proxy;
    }
    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderService = new
                OrderServiceV1Impl(orderRepositoryV1(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1)
                Proxy.newProxyInstance(DynamicProxyFilterConfig.class.getClassLoader(),
                        new Class[]{OrderServiceV1.class},
                        new LogTraceFilterHandler(orderService, logTrace, PATTERNS)
                );
        return proxy;
    }
    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1)
                Proxy.newProxyInstance(DynamicProxyFilterConfig.class.getClassLoader(),
                        new Class[]{OrderRepositoryV1.class},
                        new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS)
                );
        return proxy;
    }}
