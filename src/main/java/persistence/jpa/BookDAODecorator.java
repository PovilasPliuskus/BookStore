package persistence.jpa;

import entities.BookEntity;
import interfaces.IBookDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class BookDAODecorator implements IBookDAO {

    @Inject
    @Delegate
    private IBookDAO delegate;

    @Override
    public void save(BookEntity book) {
        System.out.println("[Decorator] saving Book with ID: " + book.getId());
        String currentTitle = book.getTitle();
        currentTitle += " DECORATED";
        book.setTitle(currentTitle);
        delegate.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        System.out.println("[Decorator] getting all Books");
        return delegate.findAll();
    }

    @Override
    public BookEntity findById(int id) {
        System.out.println("[Decorator] getting Book with ID: " + id);
        return delegate.findById(id);
    }

    @Override
    public BookEntity update(BookEntity book) {
        System.out.println("[Decorator] updating Book with ID: " + book.getId());
        return delegate.update(book);
    }

}
