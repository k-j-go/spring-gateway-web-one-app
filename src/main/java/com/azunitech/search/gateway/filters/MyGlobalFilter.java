package com.azunitech.search.gateway.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class MyGlobalFilter  implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        Map<String, String> uriVariables = ServerWebExchangeUtils.getPathPredicateVariables(exchange);
//        String segment = uriVariables.get("segment");
        log.info("MyGlobalFilter executed");
        return chain.filter(exchange);
    }
}
