package persistence.jpa;

import entities.CustomerEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class CustomerDAO {

    @Inject
    private EntityManager em;

    public void save(CustomerEntity customer) {
        em.persist(customer);
    }

    public List<CustomerEntity> findAll() {
        return em.createQuery("SELECT c FROM CustomerEntity c", CustomerEntity.class).getResultList();
    }

    public CustomerEntity findById(int id) {
        return em.find(CustomerEntity.class, id);
    }

    public void update(CustomerEntity customer) {
        em.merge(customer);
    }
}
