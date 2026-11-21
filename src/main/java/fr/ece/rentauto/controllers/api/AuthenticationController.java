package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.LoginRequestDTO;
import fr.ece.rentauto.services.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseMessage<?> login(@RequestBody LoginRequestDTO request) throws AppException {
        customUserDetailsService.login(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Authentification done");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }
}
