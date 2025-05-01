package services;

import entities.BookEntity;
import models.BookModel;
import persistence.jpa.BookDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public BookModel findBookById(int id) {
        BookEntity bookEntity = bookDAO.findById(id);
        return new BookModel(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getPageCount(),
                bookEntity.getVersion()
        );
    }

    @Transactional
    public List<BookModel> findAllBooks() {
        List<BookEntity> bookEntities = bookDAO.findAll();
        List<BookModel> bookModels = new ArrayList<BookModel>();
        for (BookEntity bookEntity : bookEntities) {
            BookModel bookModel = new BookModel();
            bookModel.setId(bookEntity.getId());
            bookModel.setTitle(bookEntity.getTitle());
            bookModel.setPageCount(bookEntity.getPageCount());
            bookModel.setVersion(bookEntity.getVersion());
            bookModels.add(bookModel);
        }

        return bookModels;
    }

    @Transactional
    public BookModel updateBook(BookModel bookModel) {
        BookEntity detached = new BookEntity();
        detached.setId(bookModel.getId());
        detached.setTitle(bookModel.getTitle());
        detached.setPageCount(bookModel.getPageCount());
        detached.setVersion(bookModel.getVersion());

        BookEntity updated = bookDAO.update(detached);

        return new BookModel(
                updated.getId(),
                updated.getTitle(),
                updated.getPageCount(),
                updated.getVersion()
        );
    }

}
