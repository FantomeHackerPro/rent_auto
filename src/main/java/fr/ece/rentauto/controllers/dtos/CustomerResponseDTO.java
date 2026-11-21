package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponseDTO {
    private Long id;

    private String name;

    private String email;

    private String phone;

    private List<BookingResponseDTO> bookings;

}
