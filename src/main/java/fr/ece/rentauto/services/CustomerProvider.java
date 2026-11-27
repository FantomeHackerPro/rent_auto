package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.UserRequestDTO;
import fr.ece.rentauto.domaines.entities.CustomerEntity;
import fr.ece.rentauto.controllers.dtos.CustomerRequestDTO;
import fr.ece.rentauto.domaines.enumerations.UserRole;
import fr.ece.rentauto.domaines.mappers.CustomerMapper;
import fr.ece.rentauto.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerProvider implements CustomerService{

    private CustomerRepository customerRepository;
    private UserService userService;

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity getCustomer(Long id) throws AppException {
        return customerRepository.findById(id).orElseThrow(() -> new AppException("Customer not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public CustomerEntity addCustomer(CustomerRequestDTO request) throws AppException {
        CustomerEntity customer = customerRepository.save(CustomerMapper.mapToCustomerToAdd(request));

        if (request.isHasAccount()) {
            UserRequestDTO userRequest = new UserRequestDTO();
            userRequest.setCustomerId(customer.getId());
            userRequest.setLogin(customer.getEmail());
            userRequest.setUsername(customer.getName());
            userRequest.setUserRole(UserRole.CUSTOMER);

            userService.addUser(userRequest);
        }
        return customer;
    }

    @Transactional
    @Override
    public void updateCustomer(Long id, CustomerRequestDTO request) throws AppException {
        customerRepository.save(CustomerMapper.mapToCustomerToUpdate(this.getCustomer(id), request));
    }

    @Transactional
    @Override
    public void deleteCustomer(Long id) {

    }
}
