package interfaces;

import entities.BookEntity;

import java.util.List;

public interface IBookDAO {
    void save(BookEntity book);
    List<BookEntity> findAll();
    BookEntity findById(int id);
    BookEntity update(BookEntity book);
}
