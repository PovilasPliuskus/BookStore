package services;

import entities.BookEntity;
import models.BookModel;
import persistence.jpa.BookDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class BookService {

    @Inject
    private BookDAO bookDAO;

    @Transactional
    public BookModel createBook(BookModel bookModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setPageCount(bookModel.getPageCount());

        bookDAO.save(bookEntity);
        bookModel.setId(bookEntity.getId());
        return bookModel;
    }
}
