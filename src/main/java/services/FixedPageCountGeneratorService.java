package services;

import interfaces.IPageCountGeneratorStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Alternative
@ApplicationScoped
public class FixedPageCountGeneratorService implements Serializable, IPageCountGeneratorStrategy {

    public CompletableFuture<Integer> generatePageCount() {

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return 999;
        });
    }
}
