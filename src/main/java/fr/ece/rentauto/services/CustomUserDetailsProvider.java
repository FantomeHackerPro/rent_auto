package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.LoginRequestDTO;
import fr.ece.rentauto.domaines.entities.CustomUserDetails;
import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.domaines.mappers.UserMapper;
import fr.ece.rentauto.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsProvider implements CustomUserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails login(LoginRequestDTO request) throws AppException {
        UserEntity user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new AppException("Authentication failed : Login is invalid", HttpStatus.BAD_REQUEST.value()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException("Authentication failed : Wrong Password", HttpStatus.BAD_REQUEST.value());
        }

        if (user.isMustChangePassword()) {
            throw new AppException("Authentication failed : You must update your password", HttpStatus.OK.value());
        }

        return new CustomUserDetails(UserMapper.mapToUserResponse(user));
    }
}