package persistence.jpa;

import entities.BookEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
}
