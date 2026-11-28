package fr.ece.rentauto.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/assets/**",
                                "/uploads/**",
                                "/login",
                                "/register",
                                "/update-password",
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/update-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/new-user").permitAll()

                        // Dashboard
                        .requestMatchers("/redirect").authenticated()

                        .requestMatchers(HttpMethod.GET, "/home").hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/admin/dashboard").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/staff/dashboard").hasAnyRole("AGENT")
                        .requestMatchers(HttpMethod.GET, "/manager/dashboard").hasAnyRole("MANAGER")

                        // Agency
                        .requestMatchers(HttpMethod.GET, "/agency", "/api/agencies/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/agencies/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/agencies/**").hasAnyRole("ADMIN")

                        // Bookings
                        .requestMatchers(HttpMethod.GET, "/customer/booking").hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/booking").hasAnyRole("MANAGER", "AGENT")
                        .requestMatchers(HttpMethod.POST, "/api/bookings/**").hasAnyRole("CUSTOMER", "AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/bookings/**").hasAnyRole("CUSTOMER")

                        // Customers
                        .requestMatchers(HttpMethod.GET, "/customer", "/api/customers/**").hasAnyRole("AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("AGENT", "MANAGER")

                        // Contract
                        .requestMatchers(HttpMethod.GET, "/api/contract/**").hasAnyRole("AGENT", "MANAGER", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/contract/**").hasAnyRole("AGENT", "MANAGER", "CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/contract/**").hasAnyRole("AGENT", "MANAGER", "CUSTOMER")

                        // Equipments
                        .requestMatchers(HttpMethod.GET, "/equipment", "/api/equipments/**").hasAnyRole("AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/equipments/**").hasAnyRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/equipments/**").hasAnyRole("MANAGER")

                        // Payments
                        .requestMatchers(HttpMethod.POST, "/api/payments/**").hasAnyRole("AGENT", "MANAGER", "CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/payments/**").hasAnyRole("AGENT", "MANAGER", "CUSTOMER")

                        // Staff
                        .requestMatchers(HttpMethod.GET, "/staff", "/api/staffs/**").hasAnyRole("AGENT", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/staffs/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/staffs/**").hasAnyRole("MANAGER", "ADMIN")

                        // Users
                        .requestMatchers(HttpMethod.GET, "/user", "/api/users/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("ADMIN")

                        // Vehicles
                        .requestMatchers(HttpMethod.GET, "/vehicle").hasAnyRole("AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/vehicles/**").hasAnyRole("MANAGER", "AGENT")
                        .requestMatchers(HttpMethod.PUT, "/api/vehicles/**").hasAnyRole("MANAGER")

                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}