package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffResponseDTO {

    private Long id;

    private String name;

    private String surname;

    private StaffRole role;

    private AgencyResponseDTO agency;

    private UserResponseDTO user;

}
