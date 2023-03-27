package com.azunitech.search.usecases;

import com.azunitech.search.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public interface UserBZ {
    List<User> findUser(FindCriteria fc);

    @AllArgsConstructor
    @Builder
    @Getter
    public static  class FindCriteria {
        private String name;
    }
}
