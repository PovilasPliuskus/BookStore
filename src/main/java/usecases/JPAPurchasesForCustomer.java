package usecases;

import lombok.Getter;
import lombok.Setter;
import models.CustomerModel;
import models.PurchaseModel;
import services.CustomerService;
import services.PurchaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
@Named("JPAPurchasesForCustomer")
@Getter
@Setter
public class JPAPurchasesForCustomer implements Serializable {

    @Inject
    private CustomerService customerService;

    @Inject
    private PurchaseService purchaseService;

    private CustomerModel customer;

    private PurchaseModel purchaseToCreate = new PurchaseModel();

    public String createPurchase() {

        customerService.addPurchase(customer.getId(), purchaseToCreate);

        return "purchases?faces-redirect=true&customerId=" + this.customer.getId();
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int customerId = Integer.parseInt(requestParams.get("customerId"));
        customer = customerService.findCustomerById(customerId);
    }
}
