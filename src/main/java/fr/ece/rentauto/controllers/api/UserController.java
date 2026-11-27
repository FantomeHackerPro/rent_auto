package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.UserRequestDTO;
import fr.ece.rentauto.controllers.dtos.UserResponseDTO;
import fr.ece.rentauto.domaines.mappers.UserMapper;
import fr.ece.rentauto.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseMessage<List<UserResponseDTO>> getAgencies() {
        List<UserResponseDTO> agencies = UserMapper.mapToUserResponseList(userService.getUsers());
        int code = HttpStatus.NOT_FOUND.value();
        if (!agencies.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<UserResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of users");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(agencies);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<UserResponseDTO> getUser(@PathVariable Long id) throws AppException {
        UserResponseDTO User = UserMapper.mapToUserResponse(userService.getUser(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (User != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<UserResponseDTO> response = new ResponseMessage<>();
        response.setMessage("User");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(User);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> addUser(@RequestBody UserRequestDTO request) throws AppException {
        userService.addUser(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<UserResponseDTO> response = new ResponseMessage<>();
        response.setMessage("User has been created successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("{id}/update")
    public ResponseMessage<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO request) throws AppException {
        userService.updateUser(id, request);
        int code = HttpStatus.OK.value();
        ResponseMessage<UserResponseDTO> response = new ResponseMessage<>();
        response.setMessage("User has been updated successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }
}
