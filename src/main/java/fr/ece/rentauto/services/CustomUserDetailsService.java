package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.LoginRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {
    UserDetails login(LoginRequestDTO request) throws AppException;
}
