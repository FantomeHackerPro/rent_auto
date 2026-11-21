package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.CustomerEntity;
import fr.ece.rentauto.controllers.dtos.CustomerRequestDTO;
import fr.ece.rentauto.domaines.mappers.CustomerMapper;
import fr.ece.rentauto.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerProvider implements CustomerService{

    private CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity getCustomerById(Long id) throws AppException {
        return customerRepository.findById(id).orElseThrow(() -> new AppException("Customer not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public void addCustomer(CustomerRequestDTO request) {
        customerRepository.save(CustomerMapper.mapToCustomerToAdd(request));
    }

    @Override
    public void updateCustomer(Long id, CustomerRequestDTO request) throws AppException {
        customerRepository.save(CustomerMapper.mapToCustomerToUpdate(this.getCustomerById(id), request));
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
