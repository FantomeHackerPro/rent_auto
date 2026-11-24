package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.PaymentMode;
import fr.ece.rentauto.domaines.enumerations.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private PaymentMode paymentMode;

    private PaymentStatus status;

    private ContractResponseDTO contract;

}
