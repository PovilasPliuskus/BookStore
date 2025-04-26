package book.store.bookstore.entities;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITLE")
    @Basic(optional = false)
    private String title;

    @Column(name = "PAGE_COUNT")
    @Basic(optional = false)
    private Integer pageCount;

    @Column
    @ManyToMany
    @JoinTable(
            name = "BOOK_PURCHASE",
            joinColumns = @JoinColumn(name = "FK_BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "FK_PURCHASE_ID")
    )
    private List<Purchase> purchases = new ArrayList<>();

    public Book() {

    }

    public Book(String title, Integer pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }
}
