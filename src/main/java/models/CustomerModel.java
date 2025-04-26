package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerModel {

    private Integer id;
    private String firstName;
    private String lastName;
    private List<PurchaseModel> purchases = new ArrayList<>();

    public CustomerModel() {

    }

    public CustomerModel(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerModel(Integer id, String firstName, String lastName, List<PurchaseModel> purchases) {
        this(id, firstName, lastName);
        this.purchases = purchases;
    }
}
