package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private PaymentMode paymentMode;

}
