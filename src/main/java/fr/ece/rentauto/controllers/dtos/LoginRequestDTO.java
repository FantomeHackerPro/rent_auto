package fr.ece.rentauto.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
