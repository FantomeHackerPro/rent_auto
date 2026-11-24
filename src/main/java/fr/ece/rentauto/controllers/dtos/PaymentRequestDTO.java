package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.PaymentMode;
import fr.ece.rentauto.domaines.enumerations.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {

    @NotNull
    private BigDecimal amount;

    private LocalDate paymentDate;

    @NotNull
    private PaymentMode paymentMode;

    private PaymentStatus status;

}
