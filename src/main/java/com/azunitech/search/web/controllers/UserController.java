package com.azunitech.search.web.controllers;

import com.azunitech.search.usecases.UserBZ;
import com.azunitech.search.usecases.impls.UserBZImpl;
import com.github.javafaker.Artist;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestController
public class UserController {
    @Autowired
    Artist artist;

    @Autowired
    UserBZImpl userBZ;

    @GetMapping("/get")
    List<User> LoadUsers(@RequestHeader Map<String, String> headers, @RequestParam("myParam") String myParamValue  ) {
        log.info("myParam: {}", myParamValue);
        headers.entrySet()
                .forEach(x -> log.info("request: {} {}", x.getKey(), x.getValue()));

        List<com.azunitech.search.domains.User> list = userBZ.findUser(UserBZ.FindCriteria.builder()
                .name("a")
                .build());

        return list.stream()
                .map(x -> User.from(x))
                .collect(Collectors.toList());
    }
}
