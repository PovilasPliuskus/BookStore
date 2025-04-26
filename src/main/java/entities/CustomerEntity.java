package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIRST_NAME")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    @Basic(optional = false)
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private List<PurchaseEntity> purchases = new ArrayList<>();

    public CustomerEntity() {

    }

    public CustomerEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerEntity(String firstName, String lastName, List<PurchaseEntity> purchases) {
        this(firstName, lastName);
        this.purchases = purchases;
    }
}
