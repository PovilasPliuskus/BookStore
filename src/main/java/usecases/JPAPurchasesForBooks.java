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
@Named("JPAPurchasesForBooks")
@Getter
@Setter
public class JPAPurchasesForBooks implements Serializable {

    @Inject
    private PurchaseService purchaseService;

    @Inject
    private BookService bookService;

    private BookModel book;

    private List<PurchaseModel> purchases = new ArrayList<>();

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int bookId = Integer.parseInt(requestParams.get("bookId"));
        book = bookService.findBookById(bookId);

        purchases = purchaseService.findPurchasesByBookId(bookId);
    }
}
