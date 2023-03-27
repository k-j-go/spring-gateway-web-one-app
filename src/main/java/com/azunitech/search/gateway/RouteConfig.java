package com.azunitech.search.gateway;

import com.azunitech.search.gateway.filters.MyGatewayFilter;
import com.azunitech.search.gateway.filters.MyRewritePathGatewayFilterFactory;
import com.azunitech.search.gateway.filters.PostGatewayFilterFactory;
import com.azunitech.search.gateway.filters.PreGatewayFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

@Configuration
@Component
public class RouteConfig {
    public static String SESSION_ID = "SESSION_ID";

    @Autowired
    MyRewritePathGatewayFilterFactory myRewritePathGatewayFilterFactory;

    @Autowired
    MyGatewayFilter myGatewayFilter;

    @Autowired
    PreGatewayFilterFactory preGatewayFilterFactory;
    @Autowired
    PostGatewayFilterFactory postGatewayFilterFactory;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("localstack_get", r ->
                        r.path("/getlocal")
                                .filters((f -> {
                                    f.filter(myRewritePathGatewayFilterFactory.apply(new MyRewritePathGatewayFilterFactory.Config()));
                                    f.filter(myGatewayFilter);
                                    //Register pre-gateway filter factory
                                    f.filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config()));
                                    //Register post-gateway filter factory
                                    f.filter(postGatewayFilterFactory.apply(new PostGatewayFilterFactory.Config()));
                                    f.addRequestHeader("head_a", "head_a_value");
                                    f.addRequestParameter("myParam", "myParam_value");
                                    f.addResponseHeader("response_header", "response_header_value");
                                    f.preserveHostHeader();
                                    return f;
                                }))
                                .uri("http://0.0.0.0:8080"))
                .route("localstack_get_path", r ->
                        r.path("/getpath/{PATH}")
                                .filters((f -> {
//                                    f.filter(myRewritePathGatewayFilterFactory.apply(new MyRewritePathGatewayFilterFactory.Config()));
                                    f.filter(myGatewayFilter);
                                    //Register pre-gateway filter factory
                                    f.filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config()));
                                    //Register post-gateway filter factory
                                    f.filter(postGatewayFilterFactory.apply(new PostGatewayFilterFactory.Config()));
                                    f.addRequestHeader("head_a", "head_a_value");
                                    f.addRequestParameter("myParam", "myParam_value");
                                    f.addResponseHeader("response_header", "response_header_value");
                                    f.preserveHostHeader();
                                    f.setPath("/get/{PATH}/{SESSION_ID}");
                                    return f;
                                }))
                                .uri("http://0.0.0.0:8080"))
                .route("data_posts", r -> {
                    r.path("/posts/**")
                            .filters((f -> {
                                f.filter(new GatewayFilter() {
                                    @Override
                                    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                                        ServerHttpRequest request = exchange.getRequest();
                                        addOriginalRequestUrl(exchange, request.getURI());

                                        String path = request.getURI()
                                                .getPath();
                                        //String newPath = path + "/" + config.getNewSegment();
                                        String newPath = "/posts/1";
                                        ServerHttpRequest newRequest = request.mutate()
                                                .path(newPath)
                                                .build();
                                        return chain.filter(exchange.mutate()
                                                .request(newRequest)
                                                .build());
                                    }
                                });
                                return f;
                            }));
                    return r.uri("http://0.0.0.0:3000");
                })
                .build();
    }
}
