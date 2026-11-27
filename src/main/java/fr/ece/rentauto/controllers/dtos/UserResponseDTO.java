package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {

    private String username;

    private String login;

    private String password;

    private UserRole type;

    private StaffResponseDTO staff;

    private CustomerResponseDTO customer;

}
