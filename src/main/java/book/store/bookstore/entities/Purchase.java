package book.store.bookstore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASE")
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "STATUS")
    @Basic(optional = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private Customer customer;

    public Purchase() {

    }

    public Purchase(String status) {
        this.status = status;
    }

    public Purchase(String status, Customer customer) {
        this(status);
        this.customer = customer;
    }
}
