package persistence.jpa;

import entities.BookEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class BookDAO {

    @Inject
    private EntityManager em;

    public void save(BookEntity book) {
        em.persist(book);
    }

    public List<BookEntity> findAll() {
        return em.createQuery("SELECT b FROM BookEntity b", BookEntity.class).getResultList();
    }

    public BookEntity findById(int id) {
        return em.find(BookEntity.class, id);
    }

    public BookEntity update(BookEntity book) {
        try {
            return em.merge(book);
        } catch (OptimisticLockException e) {
            System.err.println("Optimistic locking failed for BookEntity with ID: " + book.getId());
            throw e;
        }
    }
}
