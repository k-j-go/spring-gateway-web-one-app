package com.azunitech.search.repositories;

import com.azunitech.search.domains.User;
import com.azunitech.search.domains.UserGateway;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Repository
public class MemoryDBRepository implements UserGateway {
    private List<User> store = new ArrayList<>();

    @Override
    public String saveUser(SaveUserCommand saveUser) {
        User newUser = User.builder()
                .id(saveUser.getId())
                .name(saveUser.getName())
                .build();
        store.add(newUser);
        return newUser.getId();
    }

    @Override
    public List<User> loadUsers(LoadUserCommand loadUser) {
        return store.stream()
                .filter(x -> x.getName()
                        .equalsIgnoreCase(loadUser.getName()))
                .collect(Collectors.toList());
    }
}
