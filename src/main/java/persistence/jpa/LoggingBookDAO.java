package persistence.jpa;

import entities.BookEntity;
import interceptors.LoggedInvocation;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import java.util.List;

@RequestScoped
@Specializes
@LoggedInvocation
public class LoggingBookDAO extends BookDAO {

    @Override
    public void save(BookEntity book) {
        System.out.println("[LoggingBookDAO] saving Book with ID: " + book.getId());
        super.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        System.out.println("[LoggingBookDAO] getting all Books");
        return super.findAll();
    }

    @Override
    public BookEntity findById(int id) {
        System.out.println("[LoggingBookDAO] getting Book with ID: " + id);
        return super.findById(id);
    }

    @Override
    public BookEntity update(BookEntity book) {
        System.out.println("[LoggingBookDAO] updating Book with ID: " + book.getId());
        return super.update(book);
    }
}
