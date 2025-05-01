package services;

import entities.BookEntity;
import entities.PurchaseEntity;
import models.BookModel;
import models.PurchaseModel;
import persistence.jpa.BookDAO;
import persistence.jpa.PurchaseDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class PurchaseService {

    @Inject
    private PurchaseDAO purchaseDAO;

    @Inject
    private BookDAO bookDAO;

    @Transactional
    public PurchaseModel createPurchase(PurchaseModel purchaseModel) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setStatus(purchaseModel.getStatus());

        purchaseDAO.save(purchaseEntity);
        purchaseModel.setId(purchaseEntity.getId());
        return purchaseModel;
    }

    @Transactional
    public PurchaseModel findPurchaseById(Integer id) {
        PurchaseEntity purchaseEntity = purchaseDAO.findById(id);
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setId(purchaseEntity.getId());
        purchaseModel.setStatus(purchaseEntity.getStatus());

        List<BookModel> bookModels = new ArrayList<>();
        for (BookEntity bookEntity : purchaseEntity.getBooks()) {
            BookModel bookModel = new BookModel();
            bookModel.setId(bookEntity.getId());
            bookModel.setTitle(bookEntity.getTitle());
            bookModel.setPageCount(bookEntity.getPageCount());
            bookModels.add(bookModel);
        }
        purchaseModel.setBooks(bookModels);

        return purchaseModel;
    }

    @Transactional
    public List<PurchaseModel> findAllPurchases() {
        List<PurchaseEntity> purchaseEntities = purchaseDAO.findAll();
        List<PurchaseModel> purchaseModels = new ArrayList<>();

        for (PurchaseEntity purchaseEntity : purchaseEntities) {
            PurchaseModel purchaseModel = new PurchaseModel();
            purchaseModel.setId(purchaseEntity.getId());
            purchaseModel.setStatus(purchaseEntity.getStatus());

            List<BookModel> bookModels = new ArrayList<>();
            for (BookEntity bookEntity : purchaseEntity.getBooks()) {
                BookModel bookModel = new BookModel();
                bookModel.setId(bookEntity.getId());
                bookModel.setTitle(bookEntity.getTitle());
                bookModel.setPageCount(bookEntity.getPageCount());
                bookModels.add(bookModel);
            }
            purchaseModel.setBooks(bookModels);

            purchaseModels.add(purchaseModel);
        }

        return purchaseModels;
    }

    @Transactional
    public List<PurchaseModel> findPurchasesByBookId(Integer bookId) {
        BookEntity bookEntity = bookDAO.findById(bookId);

        List<PurchaseModel> purchaseModels = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : bookEntity.getPurchases()) {
            PurchaseModel purchaseModel = new PurchaseModel();
            purchaseModel.setId(purchaseEntity.getId());
            purchaseModel.setStatus(purchaseEntity.getStatus());

            List<BookModel> bookModels = new ArrayList<>();
            for (BookEntity be : purchaseEntity.getBooks()) {
                BookModel bm = new BookModel();
                bm.setId(be.getId());
                bm.setTitle(be.getTitle());
                bm.setPageCount(be.getPageCount());
                bookModels.add(bm);
            }
            purchaseModel.setBooks(bookModels);

            purchaseModels.add(purchaseModel);
        }

        return purchaseModels;
    }

    @Transactional
    public PurchaseModel updatePurchase(PurchaseModel purchaseModel) {
        PurchaseEntity purchaseEntity = purchaseDAO.findById(purchaseModel.getId());
        purchaseEntity.setStatus(purchaseModel.getStatus());

        purchaseDAO.update(purchaseEntity);

        return purchaseModel;
    }

    @Transactional
    public void addBook(Integer purchaseId, Integer bookId) {

        System.out.println("purchaseId from request: " + purchaseId);
        System.out.println("bookId from request: " + bookId);

        PurchaseEntity purchaseEntity = purchaseDAO.findById(purchaseId);

        purchaseEntity.ToString();

        BookEntity bookEntity = bookDAO.findById(bookId);

        bookEntity.ToString();

        purchaseEntity.getBooks().add(bookEntity);
        bookEntity.getPurchases().add(purchaseEntity);

        purchaseDAO.update(purchaseEntity);
    }
}
