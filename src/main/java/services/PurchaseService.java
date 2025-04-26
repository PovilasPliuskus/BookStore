package services;

import entities.PurchaseEntity;
import models.PurchaseModel;
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
            purchaseModels.add(purchaseModel);
        }

        return purchaseModels;
    }
}
