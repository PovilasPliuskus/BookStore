package persistence.jpa;

import book.store.bookstore.entities.Purchase;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class PurchaseDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public void save(Purchase purchase) {
        em.persist(purchase);
    }

    public List<Purchase> findAll() {
        return em.createQuery("SELECT p FROM Purchase p", Purchase.class).getResultList();
    }

    public Purchase findById(int id) {
        return em.find(Purchase.class, id);
    }
}
