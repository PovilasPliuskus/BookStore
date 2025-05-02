package services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class PageCountGeneratorService implements Serializable {

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
