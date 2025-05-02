package services;

import interfaces.IPageCountGeneratorStrategy;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class RandomPageCountGeneratorService implements Serializable, IPageCountGeneratorStrategy {

    public CompletableFuture<Integer> generatePageCount() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return new Random().nextInt(500);
        });
    }
}
