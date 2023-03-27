package com.azunitech.search;

import com.azunitech.search.gateway.RouteConfig;
import com.azunitech.search.repositories.RepoConfig;
import com.azunitech.search.web.WebConfig;
import com.github.javafaker.Artist;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebConfig.class, RepoConfig.class, RouteConfig.class})
public class Wire {
    @Bean
    public Artist getFakerAddress(){
        return new Faker().artist();
    }
}
