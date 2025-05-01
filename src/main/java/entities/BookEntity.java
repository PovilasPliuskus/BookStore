package entities;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITLE")
    @Basic(optional = false)
    private String title;

    @Column(name = "PAGE_COUNT")
    @Basic(optional = false)
    private Integer pageCount;

    @Version
    private int version;

    @Column
    @ManyToMany
    @JoinTable(
            name = "BOOK_PURCHASE",
            joinColumns = @JoinColumn(name = "FK_BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "FK_PURCHASE_ID")
    )
    private List<PurchaseEntity> purchases = new ArrayList<>();

    public BookEntity() {

    }

    public BookEntity(String title, Integer pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    public void ToString()
    {
        System.out.println("BookEntity:");
        System.out.println("id: " + id);
        System.out.println("title: " + title);
        System.out.println("pageCount: " + pageCount);
    }
}
