package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.controllers.dtos.UserRequestDTO;

import java.util.List;

public interface UserService {
    void initializeManager();
    List<UserEntity> getUsers();
    UserEntity getUser(Long id) throws AppException;
    void addUser(UserRequestDTO request) throws AppException;
    void updateUser(Long id, UserRequestDTO request) throws AppException;
}
