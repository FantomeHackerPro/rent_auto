package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.PasswordUpdateRequestDTO;
import fr.ece.rentauto.domaines.entities.CustomerEntity;
import fr.ece.rentauto.domaines.entities.StaffEntity;
import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.domaines.enumerations.StaffRole;
import fr.ece.rentauto.domaines.enumerations.UserRole;
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
    public void initializeManager() {
        if (userRepository.existsByRole(UserRole.ADMIN)) {
            return;
        }

        UserEntity user = new UserEntity();
        user.setPassword(passwordEncoder.encode("admin"));
        user.setLogin("admin");
        user.setMustChangePassword(true);
        user.setRole(UserRole.ADMIN);
        user.setUsername("Admin");

        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(Long id) throws AppException {
        return this.userRepository.findById(id).orElseThrow(() -> new AppException("User not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public UserEntity getUser(String login) throws AppException {
        return this.userRepository.findByLogin(login).orElseThrow(() -> new AppException("User not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addUser(UserRequestDTO request) throws AppException {
        userExistByLogin(request.getLogin());

        request.setPassword(passwordEncoder.encode("user"));
        UserEntity user = UserMapper.mapToUserToAdd(request);
        user.setMustChangePassword(true);

        if (request.getStaffId() != null) {
            StaffEntity staff = this.staffRepository.findById(request.getStaffId()).orElseThrow(() -> new AppException("Staff not found.", HttpStatus.NOT_FOUND.value()));
            UserRole role = staff.getRole().equals(StaffRole.AGENT) ? UserRole.AGENT : UserRole.MANAGER;
            user.setUsername(staff.getName()+ ' ' +staff.getSurname());
            user.setLogin(staff.getEmail());
            user.setRole(role);
            user.setStaff(staff);
        }else{
            CustomerEntity customer = this.customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new AppException("Customer not found.", HttpStatus.NOT_FOUND.value()));
            user.setLogin(customer.getEmail());
            user.setUsername(customer.getName());
            user.setRole(UserRole.CUSTOMER);
            user.setCustomer(customer);
        }

        userRepository.save(user);
    }

    @Override
    public void createUser(UserRequestDTO request) throws AppException {
        userExistByLogin(request.getLogin());
        UserEntity user = UserMapper.mapToUserToAdd(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setMustChangePassword(false);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(PasswordUpdateRequestDTO request) throws AppException {
        UserEntity user = this.getUser(request.getLogin());
        if (!passwordEncoder.matches(request.getActualPassword(), user.getPassword())) {
            throw new AppException("Update password process is failed : Wrong actual password", HttpStatus.BAD_REQUEST.value());
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setMustChangePassword(false);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(Long id, UserRequestDTO request) throws AppException {
        this.userRepository.save(UserMapper.mapToUserToUpdate(this.getUser(id), request));
    }

    private void userExistByLogin(String login) throws AppException {
        if(this.userRepository.existsByLogin(login)) {
            throw new AppException("User are already exist.", HttpStatus.BAD_REQUEST.value());
        }
    }
}