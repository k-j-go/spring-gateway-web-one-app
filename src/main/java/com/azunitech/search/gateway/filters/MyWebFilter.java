package com.azunitech.search.gateway.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class MyWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("{} {}",exchange.getRequest().getLocalAddress().getHostString(), exchange.getRequest().getPath());
        log.info("MyWebFilter executed");
        return chain.filter(exchange);
    }
}
