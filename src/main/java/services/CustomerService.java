package services;

import entities.CustomerEntity;
import models.CustomerModel;
import persistence.jpa.CustomerDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    @Transactional
    public CustomerModel createCustomer(CustomerModel customerModel) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerModel.getFirstName());
        customerEntity.setLastName(customerModel.getLastName());

        customerDAO.save(customerEntity);
        customerModel.setId(customerEntity.getId());
        return customerModel;
    }

    @Transactional
    public CustomerModel findCustomerById(Integer id) {
        CustomerEntity customerEntity = customerDAO.findById(id);
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerEntity.getId());
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());

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
            customerModels.add(customerModel);
        }

        return customerModels;
    }
}
