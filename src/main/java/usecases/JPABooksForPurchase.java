package usecases;

import lombok.Getter;
import lombok.Setter;
import models.BookModel;
import models.PurchaseModel;
import services.BookService;
import services.PurchaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
@Named("JPABooksForPurchase")
@Getter
@Setter
public class JPABooksForPurchase implements Serializable {

    @Inject
    private PurchaseService purchaseService;

    private PurchaseModel purchase;

    private BookModel bookToAdd = new BookModel();
    @Inject
    private BookService bookService;

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int purchaseId = Integer.parseInt(requestParams.get("purchaseId"));
        purchase = purchaseService.findPurchaseById(purchaseId);
    }
}
