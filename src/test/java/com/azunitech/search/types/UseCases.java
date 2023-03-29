package com.azunitech.search.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface UseCases {
    DomainTypeLoadResponse loadDomains(DomainTypeLoadRequest request);

    @Builder
    @AllArgsConstructor
    @Log4j2
    public class DomainTypeLoadResponse {
        public void view(Consumer<DomainTypes> consumer) {
            list.stream()
                    .forEach(x -> consumer.accept(x));
        }

        /**
         * If you want to get a value from a Flux or Mono, you have to use callback (
         * Consumer to get it
         * @param consumer
         */
        public void viewFlux(Consumer<DomainTypes> consumer) {
            Disposable r = Flux.fromIterable(list)
                    .map(x -> {
                        Supplier<CompletableFuture<DomainTypes>> supplier = () -> CompletableFuture.completedFuture(DomainTypes.builder()
                                .id("a")
                                .name("ff")
                                .build());
                        return Mono.fromFuture(supplier).
                                timeout(Duration.ofSeconds(1l))
                                .doOnError(e -> log.info(e.getMessage()));
                    })
                    .doOnNext(x -> x.subscribe(y -> consumer.accept(y)))
                    .subscribe();
        }

        private List<DomainTypes> list;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public class DomainTypeLoadRequest {
    }
}
