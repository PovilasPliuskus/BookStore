package persistence.jpa;

import book.store.bookstore.entities.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class CustomerDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    public List<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public Customer findById(int id) {
        return em.find(Customer.class, id);
    }
}
