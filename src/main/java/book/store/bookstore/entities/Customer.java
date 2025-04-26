package book.store.bookstore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
public class Customer {

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
    private List<Purchase> purchases = new ArrayList<>();

    public Customer() {

    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, List<Purchase> purchases) {
        this(firstName, lastName);
        this.purchases = purchases;
    }
}
