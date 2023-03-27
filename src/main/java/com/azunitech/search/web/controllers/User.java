package com.azunitech.search.web.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String id;
    private String name;

    public static User from(com.azunitech.search.domains.User u) {
        return User.builder().name(u.getName()).id(u.getId()).build();
    }
}
