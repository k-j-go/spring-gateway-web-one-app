package com.azunitech.search.types.impls;

import com.azunitech.search.types.DomainTypes;
import com.azunitech.search.types.UseCases;

import java.util.Arrays;
import java.util.Collections;

public class UseCasesImpl implements UseCases {
    @Override
    public DomainTypeLoadResponse loadDomains(DomainTypeLoadRequest request) {
        return DomainTypeLoadResponse.builder()
                .list(Collections.unmodifiableList(Arrays.asList(DomainTypes.builder()
                        .id("1")
                        .name("fake")
                        .build())))
                .build();
    }
}
