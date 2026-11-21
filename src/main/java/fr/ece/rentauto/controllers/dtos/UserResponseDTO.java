package fr.ece.rentauto.controllers.dtos;

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

    private StaffResponseDTO staff;

    private CustomerResponseDTO customer;

}
