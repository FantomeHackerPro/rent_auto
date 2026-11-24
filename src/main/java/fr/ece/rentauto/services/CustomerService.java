package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.CustomerEntity;
import fr.ece.rentauto.controllers.dtos.CustomerRequestDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getCustomers();
    CustomerEntity getCustomer(Long id) throws AppException;
    void addCustomer(CustomerRequestDTO request) throws AppException;
    void updateCustomer(Long id, CustomerRequestDTO request) throws AppException;
    void deleteCustomer(Long id);
}
