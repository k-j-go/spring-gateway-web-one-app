package com.azunitech.search.gateway.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Log4j2
@Component
public class UrlTemplateGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Map<String, String> maps = ServerWebExchangeUtils.getUriTemplateVariables(exchange);
        maps.entrySet()
                .stream()
                .forEach(x -> {
                    log.info("url path: {}--{}", x.getKey(), x.getValue());
                });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
