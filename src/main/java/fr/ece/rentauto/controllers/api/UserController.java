package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;


}
