package com.azunitech.search.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserGateway {
    String saveUser(SaveUserCommand saveUser);

    List<User> loadUsers(LoadUserCommand loadUser);

    @Builder
    @Getter
    @AllArgsConstructor
    public class SaveUserCommand {
        private String id;
        private String name;
    }


    @AllArgsConstructor
    @Builder
    @Getter
    public class LoadUserCommand {
        private String name;
    }
}


