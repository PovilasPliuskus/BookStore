package persistence.jpa;

import book.store.bookstore.entities.Book;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class BookDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public void save(Book book) {
        em.persist(book);
    }

    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Book findById(int id) {
        return em.find(Book.class, id);
    }
}
