package com.azunitech.search.web.controllers;

import com.azunitech.search.usecases.UserBZ;
import com.azunitech.search.usecases.impls.UserBZImpl;
import com.github.javafaker.Artist;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{path}/{session_id}")
    ResponseEntity<List<User>> LoadUsers(@RequestHeader Map<String, String> headers,
                                         @RequestParam("myParam") String myParamValue,
                                         @PathVariable String path,
                                         @PathVariable String session_id) {
        log.info("myParam: {}", myParamValue);
        log.info("path: {}", path);
        log.info("session_id: {}", session_id);
        headers.entrySet()
                .forEach(x -> log.info("request: {} {}", x.getKey(), x.getValue()));

        List<com.azunitech.search.domains.User> list = userBZ.findUser(UserBZ.FindCriteria.builder()
                .name("a")
                .build());

        List<User> result = list.stream()
                .map(x -> User.from(x))
                .map(x -> {
                            x.setName(session_id);
                            return x;
                        }
                )
                .collect(Collectors.toList());

        return new ResponseEntity<List<User>>(result, HttpStatus.OK);
    }
}
