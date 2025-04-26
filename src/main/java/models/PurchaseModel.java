package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PurchaseModel {

    private Integer id;
    private String status;
    private List<BookModel> books = new ArrayList<>();

    public PurchaseModel() {

    }

    public PurchaseModel(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public PurchaseModel(Integer id, String status, List<BookModel> books) {
        this(id, status);
        this.books = books;
    }
}
