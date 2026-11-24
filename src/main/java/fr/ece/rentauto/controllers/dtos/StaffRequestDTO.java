package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.StaffRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String email;

    @NotEmpty
    private StaffRole role;

    @NotEmpty
    private Long agencyId;

    private boolean hasAccount;

}
