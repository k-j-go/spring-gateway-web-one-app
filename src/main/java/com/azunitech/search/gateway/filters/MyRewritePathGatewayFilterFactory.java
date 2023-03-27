package com.azunitech.search.gateway.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

@Log4j2
@Component
public class MyRewritePathGatewayFilterFactory extends AbstractGatewayFilterFactory<MyRewritePathGatewayFilterFactory.Config> {


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            addOriginalRequestUrl(exchange, request.getURI());

            String path = request.getURI()
                    .getPath();
            //String newPath = path + "/" + config.getNewSegment();
            String newPath = "/get";
            ServerHttpRequest newRequest = request.mutate()
                    .path(newPath)
                    .build();
            return chain.filter(exchange.mutate()
                    .request(newRequest)
                    .build());
        };
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Config {
        private String newSegment;
    }
}
