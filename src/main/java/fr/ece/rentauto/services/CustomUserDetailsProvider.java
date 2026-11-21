package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.LoginRequestDTO;
import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsProvider implements CustomUserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails login(LoginRequestDTO request) throws AppException {
        UserEntity appUser = userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                    .orElseThrow(() -> new AppException("Authentication failed : Login or Password it invalid", HttpStatus.NOT_FOUND.value()));

        // On récupère le rôle depuis le Staff associé (si présent) ou on met un rôle par défaut
        String role = appUser.getStaff().getRole().name();

        return org.springframework.security.core.userdetails.User.builder()
                .username(appUser.getLogin()) // Spring Security utilise username pour stocker l'identifiant principal
                .password(appUser.getPassword())
                .roles(role) // Ex: ADMIN, EMPLOYEE, etc.
                .build();
    }
}