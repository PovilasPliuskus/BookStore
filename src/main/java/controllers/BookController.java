package controllers;

import entities.BookEntity;
import models.BookModel;
import services.BookService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("book")
public class BookController {

    @Inject
    BookService bookService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(BookModel bookModel) {
        BookModel response = bookService.createBook(bookModel);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") int id) {
        BookModel response = bookService.findBookById(id);

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        List<BookModel> response = bookService.findAllBooks();

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(BookModel bookModel) {
        try {
            BookModel response = bookService.updateBook(bookModel);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Book was updated by someone else. Please reload and try again.")
                    .build();
        }
    }
}
