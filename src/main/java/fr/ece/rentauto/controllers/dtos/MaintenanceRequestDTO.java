package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaintenanceRequestDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Long vehicleId;

}
