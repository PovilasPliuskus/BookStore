package controllers;

import models.CustomerModel;
import models.PurchaseModel;
import services.CustomerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerModel customerModel) {
        CustomerModel response = customerService.createCustomer(customerModel);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Integer id) {
        CustomerModel response = customerService.findCustomerById(id);

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<CustomerModel> response = customerService.findAllCustomers();

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerModel customerModel) {
        CustomerModel response = customerService.updateCustomer(customerModel);

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }
}
