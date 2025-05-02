package interfaces;

import java.util.concurrent.CompletableFuture;

public interface IPageCountGeneratorStrategy {

    CompletableFuture<Integer> generatePageCount();
}
