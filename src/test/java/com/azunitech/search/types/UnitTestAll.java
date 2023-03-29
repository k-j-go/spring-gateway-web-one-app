package com.azunitech.search.types;

import com.azunitech.search.types.impls.UseCasesImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class UnitTestAll {
    @Test
    public void generalTest() {
        UseCasesImpl impl = new UseCasesImpl();
        UseCases.DomainTypeLoadResponse respose = impl.loadDomains(UseCases.DomainTypeLoadRequest.builder()
                .build());

        respose.view(x -> {
            log.info(x);
        });

        respose.viewFlux(x -> {
            log.info(x);
        });

    }
}
