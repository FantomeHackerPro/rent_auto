package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingPayloadRequestDTO {

    private BookingRequestDTO bookingRequest;

    private ContractRequestDTO contractRequest;

}
