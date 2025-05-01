package persistence.jpa;

import entities.PurchaseEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class PurchaseDAO {

    @Inject
    private EntityManager em;

    public void save(PurchaseEntity purchase) {
        em.persist(purchase);
    }

    public List<PurchaseEntity> findAll() {
        return em.createQuery("SELECT p FROM PurchaseEntity p", PurchaseEntity.class).getResultList();
    }

    public PurchaseEntity findById(int id) {
        return em.find(PurchaseEntity.class, id);
    }

    public PurchaseEntity update(PurchaseEntity purchase) {
        return em.merge(purchase);
    }
}
