package controllers;

import models.PurchaseModel;
import services.PurchaseService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("purchase")
public class PurchaseController {

    @Inject
    PurchaseService purchaseService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPurchase(PurchaseModel purchaseModel) {
        PurchaseModel response = purchaseService.createPurchase(purchaseModel);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchase(@PathParam("id") Integer id) {
        PurchaseModel response = purchaseService.findPurchaseById(id);

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPurchases() {
        List<PurchaseModel> response = purchaseService.findAllPurchases();

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }
}
