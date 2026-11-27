package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.controllers.dtos.UserRequestDTO;
import fr.ece.rentauto.controllers.dtos.UserResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity mapToUserToAdd(UserRequestDTO request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setRole(request.getUserRole());
        return user;
    }

    public static UserEntity mapToUserToUpdate(UserEntity user, UserRequestDTO request) {
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        return user;
    }

    public static UserResponseDTO mapToUserResponse(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO response = new UserResponseDTO();
        response.setLogin(user.getLogin());
        response.setPassword(user.getPassword());
        response.setUsername(user.getUsername());
        response.setType(user.getRole());
        response.setStaff(StaffMapper.mapToStaffSummaryResponse(user.getStaff()));
        response.setCustomer(CustomerMapper.mapToCustomerSummaryResponse(user.getCustomer()));
        return response;
    }

    public static UserResponseDTO mapToUserSummaryResponse(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO response = new UserResponseDTO();
        response.setLogin(user != null ? user.getLogin() : null);
        response.setPassword(user != null ? user.getPassword() : null);
        response.setUsername(user != null ? user.getUsername() : null);
        response.setType(user != null ? user.getRole() : null);
        return response;
    }

    public static List<UserResponseDTO> mapToUserResponseList(List<UserEntity> users) {
        if (users.isEmpty()) {
            return new ArrayList<>();
        }
        return users.stream().map(UserMapper::mapToUserResponse).collect(Collectors.toList());
    }
}
