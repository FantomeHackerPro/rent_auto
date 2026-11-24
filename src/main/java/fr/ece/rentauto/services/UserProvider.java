package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.CustomerEntity;
import fr.ece.rentauto.domaines.entities.StaffEntity;
import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.domaines.mappers.UserMapper;
import fr.ece.rentauto.repositories.CustomerRepository;
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
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(Long id) throws AppException {
        return this.userRepository.findById(id).orElseThrow(() -> new AppException("User not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addUser(UserRequestDTO request) throws AppException {
        if(this.userRepository.existsByLogin(request.getLogin())) {
           throw new AppException("User are already exist.", HttpStatus.BAD_REQUEST.value());
        }

        request.setPassword(passwordEncoder.encode("user"));
        UserEntity user = UserMapper.mapToUserToAdd(request);

        if (request.getStaffId() != null) {
            StaffEntity staff = this.staffRepository.findById(request.getStaffId()).orElseThrow(() -> new AppException("Staff not found.", HttpStatus.NOT_FOUND.value()));
            user.setStaff(staff);
        }else{
            CustomerEntity customer = this.customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new AppException("Customer not found.", HttpStatus.NOT_FOUND.value()));
            user.setCustomer(customer);
        }

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(Long id, UserRequestDTO request) throws AppException {
        this.userRepository.save(UserMapper.mapToUserToUpdate(this.getUser(id), request));
    }
}