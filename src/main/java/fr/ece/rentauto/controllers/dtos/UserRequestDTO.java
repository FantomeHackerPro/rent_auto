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
    private UserRole userRole;

    private Long staffId;

    private Long customerId;

    @AssertTrue(message = "L'identifiant correspondant au type d'utilisateur (staffId ou customerId) est obligatoire")
    public boolean isValidUserTypeId() {
        if (userRole == null) {
            return true; // Laisse @NotNull gérer ce cas pour éviter un doublon d'erreur
        }

        // Adaptez les valeurs de l'enum selon votre classe UserType
        return switch (userRole) {
            case AGENT -> staffId != null && customerId == null;
            case CUSTOMER -> customerId != null && staffId == null;
            default -> true; // Pour d'autres rôles éventuels (ex: ADMIN)
        };
    }

}
