package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractResponseDTO {

    private Long id;

    private LocalDate signatureDate;

    private BigDecimal price;

    private boolean validity;

    private BookingResponseDTO  booking;

    private List<PaymentResponseDTO> payments;

}
