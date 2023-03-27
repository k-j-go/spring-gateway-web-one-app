package com.azunitech.search.gateway.filters;

import com.github.javafaker.Artist;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.azunitech.search.gateway.RouteConfig.SESSION_ID;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.putUriTemplateVariables;

@Component
@Log4j2
public class MyGatewayFilter implements GatewayFilter, Ordered {

    @Autowired
    Artist artist;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("MyGatewayFilter executed");
        String session_id = artist.name();
        exchange.getAttributes().put(SESSION_ID, session_id);
        putUriTemplateVariables(exchange, Collections.singletonMap(SESSION_ID, session_id));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
