package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractRequestDTO {

    private Long id;

    private String signatureDate;

    private BigDecimal price;

    private boolean validity;

}
