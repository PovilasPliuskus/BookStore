package usecases;

import lombok.Getter;
import lombok.Setter;
import models.CustomerModel;
import services.CustomerService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Model
@Named("JPACustomers")
@Getter
@Setter
public class JPACustomers implements Serializable {

    @Inject
    private CustomerService customerService;

    private List<CustomerModel> customers;
    private CustomerModel customerToCreate = new CustomerModel();

    public String createCustomer()
    {
        customerService.createCustomer(customerToCreate);
        return "index?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        customers = customerService.findAllCustomers();
    }
}
