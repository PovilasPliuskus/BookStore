package controllers;

import interfaces.IPageCountGeneratorStrategy;
import services.FixedPageCountGeneratorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Path("/pageCount")
public class PageCountGeneratorController {

    private CompletableFuture<Integer> pageCountGenerationTask;

    @Inject
    private IPageCountGeneratorStrategy pageCountGeneratorService;

    @POST
    @Path("/start")
    public Response startGeneration() {
        pageCountGenerationTask = pageCountGeneratorService.generatePageCount();
        return Response.accepted("Page count generation started").build();
    }

    @GET
    @Path("/status")
    @Produces("text/plain")
    public String getStatus() throws InterruptedException, ExecutionException {
        if (pageCountGenerationTask == null) {
            return "No task started";
        }
        else if (pageCountGenerationTask.isDone()) {
            return "Page count generated: " + pageCountGenerationTask.get();
        }
        else {
            return "Page count generation in progress";
        }
    }
}
