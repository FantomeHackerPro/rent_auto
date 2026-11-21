package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentResponseDTO {

    private Long id;

    private String label;

    private List<VehicleResponseDTO> vehicles;

}
