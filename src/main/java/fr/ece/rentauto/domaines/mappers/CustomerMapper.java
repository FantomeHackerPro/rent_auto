package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.controllers.dtos.CustomerRequestDTO;
import fr.ece.rentauto.controllers.dtos.CustomerResponseDTO;
import fr.ece.rentauto.domaines.entities.CustomerEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerEntity mapToCustomerToAdd(CustomerRequestDTO request) {
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail(request.getEmail());
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setCreatedAt(LocalDateTime.now());
        return customer;
    }

    public static CustomerEntity mapToCustomerToUpdate(CustomerEntity customerToUpdate, CustomerRequestDTO request) {
        customerToUpdate.setEmail(request.getEmail());
        customerToUpdate.setName(request.getName());
        customerToUpdate.setUpdatedAt(LocalDateTime.now());
        return customerToUpdate;
    }

    public static CustomerResponseDTO mapToCustomerResponse(CustomerEntity customer) {
        CustomerResponseDTO response = new CustomerResponseDTO();
        response.setId(customer.getId());
        response.setEmail(customer.getEmail());
        response.setName(customer.getName());
        response.setPhone(customer.getPhone());
        response.setBookings(BookingMapper.mapToBookingSummaryResponseList(customer.getBookings()));
        return response;
    }

    public static CustomerResponseDTO mapToCustomerSummaryResponse(CustomerEntity customer) {
        CustomerResponseDTO response = new CustomerResponseDTO();
        response.setId(customer.getId());
        response.setEmail(customer.getEmail());
        response.setName(customer.getName());
        response.setPhone(customer.getPhone());
        return response;
    }

    public static List<CustomerResponseDTO> mapToCustomerResponseList(List<CustomerEntity> customerEntities) {
        if (customerEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return customerEntities.stream().map(CustomerMapper::mapToCustomerResponse).collect(Collectors.toList());
    }
}
