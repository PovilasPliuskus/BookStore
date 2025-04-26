package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASE")
@Getter
@Setter
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "STATUS")
    @Basic(optional = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private CustomerEntity customer;

    public PurchaseEntity() {

    }

    public PurchaseEntity(String status) {
        this.status = status;
    }

    public PurchaseEntity(String status, CustomerEntity customer) {
        this(status);
        this.customer = customer;
    }
}
