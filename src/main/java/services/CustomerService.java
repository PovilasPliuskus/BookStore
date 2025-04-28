package services;

import entities.CustomerEntity;
import entities.PurchaseEntity;
import models.CustomerModel;
import models.PurchaseModel;
import persistence.jpa.CustomerDAO;
import persistence.jpa.PurchaseDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    @Inject
    private PurchaseDAO purchaseDAO;

    @Transactional
    public CustomerModel createCustomer(CustomerModel customerModel) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerModel.getFirstName());
        customerEntity.setLastName(customerModel.getLastName());

        customerDAO.save(customerEntity);
        customerModel.setId(customerEntity.getId());
        return customerModel;
    }

    public CustomerModel findCustomerById(Integer id) {
        CustomerEntity customerEntity = customerDAO.findById(id);
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerEntity.getId());
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());

        List<PurchaseModel> purchaseModels = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : customerEntity.getPurchases()) {
            PurchaseModel purchaseModel = new PurchaseModel();
            purchaseModel.setId(purchaseEntity.getId());
            purchaseModel.setStatus(purchaseEntity.getStatus());
            purchaseModels.add(purchaseModel);
        }
        customerModel.setPurchases(purchaseModels);

        return customerModel;
    }

    @Transactional
    public List<CustomerModel> findAllCustomers() {
        List<CustomerEntity> customerEntities = customerDAO.findAll();
        List<CustomerModel> customerModels = new ArrayList<>();

        for (CustomerEntity customerEntity : customerEntities) {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setId(customerEntity.getId());
            customerModel.setFirstName(customerEntity.getFirstName());
            customerModel.setLastName(customerEntity.getLastName());

            List<PurchaseModel> purchaseModels = new ArrayList<>();
            for (PurchaseEntity purchaseEntity : customerEntity.getPurchases()) {
                PurchaseModel purchaseModel = new PurchaseModel();
                purchaseModel.setId(purchaseEntity.getId());
                purchaseModel.setStatus(purchaseEntity.getStatus());
                purchaseModels.add(purchaseModel);
            }
            customerModel.setPurchases(purchaseModels);

            customerModels.add(customerModel);
        }

        return customerModels;
    }

    @Transactional
    public void addPurchase(Integer customerId, PurchaseModel purchaseModel) {
        CustomerEntity customerEntity = customerDAO.findById(customerId);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setStatus(purchaseModel.getStatus());
        purchaseEntity.setCustomer(customerEntity);

        //komentaras

        purchaseDAO.save(purchaseEntity);
    }
}
