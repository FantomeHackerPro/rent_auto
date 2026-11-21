package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponseDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;


    private BookingStatus status;

    private VehicleResponseDTO vehicle;

    private ContractResponseDTO contract;

    private CustomerResponseDTO customer;

}
