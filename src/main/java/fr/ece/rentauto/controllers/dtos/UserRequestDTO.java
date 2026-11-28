package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.UserRole;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String login;

    private String password;

    @NotNull
    private UserRole role;

    private Long staffId;

    private Long customerId;

}
