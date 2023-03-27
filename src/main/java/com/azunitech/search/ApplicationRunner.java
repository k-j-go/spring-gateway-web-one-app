package com.azunitech.search;

import com.azunitech.search.domains.UserGateway;
import com.azunitech.search.repositories.MemoryDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    MemoryDBRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveUser( UserGateway.SaveUserCommand.builder().id("1").name("a").build());
    }
}
