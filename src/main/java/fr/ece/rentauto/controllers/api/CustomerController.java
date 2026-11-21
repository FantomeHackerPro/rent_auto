package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.CustomerRequestDTO;
import fr.ece.rentauto.controllers.dtos.CustomerResponseDTO;
import fr.ece.rentauto.domaines.mappers.CustomerMapper;
import fr.ece.rentauto.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public ResponseMessage<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = CustomerMapper.mapToCustomerResponseList(customerService.getCustomers());
        int code = HttpStatus.NOT_FOUND.value();
        if (!customers.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<CustomerResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of customers");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(customers);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<CustomerResponseDTO> getCustomer(@PathVariable Long id) throws AppException {
        CustomerResponseDTO customer = CustomerMapper.mapToCustomerResponse(customerService.getCustomerById(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (customer != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<CustomerResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Customer");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(customer);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> createCustomer(@RequestBody CustomerRequestDTO request) throws AppException {
        customerService.addCustomer(request);
        int code = HttpStatus.OK.value();
        ResponseMessage<CustomerResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Customer has been created successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("{id}/update")
    public ResponseMessage<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDTO request) throws AppException {
        customerService.updateCustomer(id, request);
        int code = HttpStatus.OK.value();
        ResponseMessage<CustomerResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Customer has been updated successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

}
