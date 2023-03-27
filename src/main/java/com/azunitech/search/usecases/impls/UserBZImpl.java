package com.azunitech.search.usecases.impls;

import com.azunitech.search.domains.User;
import com.azunitech.search.domains.UserGateway;
import com.azunitech.search.repositories.MemoryDBRepository;
import com.azunitech.search.usecases.UserBZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBZImpl implements UserBZ {
    @Autowired
    MemoryDBRepository memoryDBRepository;
    @Override
    public List<User> findUser(FindCriteria fc) {
        UserGateway.LoadUserCommand cmd = UserGateway.LoadUserCommand.builder()
                .name("a")
                .build();
        return memoryDBRepository.loadUsers(cmd);
    }
}
