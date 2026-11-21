package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.StaffEntity;
import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.domaines.mappers.UserMapper;
import fr.ece.rentauto.repositories.StaffRepository;
import fr.ece.rentauto.repositories.UserRepository;
import fr.ece.rentauto.controllers.dtos.UserRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserProvider implements UserService {

    private StaffRepository staffRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) throws AppException {
        return this.userRepository.findById(id).orElseThrow(() -> new AppException("User not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addUser(UserRequestDTO request) throws AppException {
        StaffEntity staff = this.staffRepository.findById(request.getStaffId()).orElseThrow(() -> new AppException("Staff not found.", HttpStatus.NOT_FOUND.value()));
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity user = UserMapper.mapToUserToAdd(request);
        user.setStaff(staff);
        userRepository.save(user);
        staff.setUser(user);
        staffRepository.save(staff);
    }

    @Override
    public void updateUser(Long id, UserRequestDTO request) throws AppException {
        this.userRepository.save(UserMapper.mapToUserToUpdate(this.getUserById(id), request));
    }
}