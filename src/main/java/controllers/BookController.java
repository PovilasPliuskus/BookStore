package controllers;

import entities.BookEntity;
import models.BookModel;
import services.BookService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
