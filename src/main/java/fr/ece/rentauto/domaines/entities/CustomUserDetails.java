package fr.ece.rentauto.domaines.entities;

import fr.ece.rentauto.controllers.dtos.CustomerResponseDTO;
import fr.ece.rentauto.controllers.dtos.StaffResponseDTO;
import fr.ece.rentauto.controllers.dtos.UserResponseDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String login;
    private final String displayName;
    private final String password;
    private final CustomerResponseDTO customer;
    private final StaffResponseDTO staff;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserResponseDTO user) {
        this.userId = user.getId();
        this.login = user.getLogin();
        this.displayName = user.getUsername();
        this.password = user.getPassword();
        this.customer = user.getCustomer();
        this.staff = user.getStaff();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    public Long getCustomerId() {
        return customer != null ? customer.getId() : null; // adaptez le getter selon CustomerEntity
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        // Retourne le login comme identifiant principal Spring Security
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}