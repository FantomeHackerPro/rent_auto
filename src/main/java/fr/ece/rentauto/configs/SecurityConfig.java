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
                                "/home",
                                "/",
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/vehicles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/bookings/estimation").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/bookings/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/customers/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/payments/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/payments/**").permitAll()

                        // Dashboard
                        .requestMatchers("/dashboard").authenticated()

                        // Agency
                        .requestMatchers(HttpMethod.GET, "/agency", "/api/agencies/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/agencies/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/agencies/**").hasAnyRole("ADMIN")

                        // Bookings
                        .requestMatchers(HttpMethod.GET, "/booking", "/api/bookings/**").hasAnyRole("CUSTOMER", "AGENT", "MANAGER")
//                        .requestMatchers(HttpMethod.POST, "/api/bookings/**").hasAnyRole("CUSTOMER", "AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/bookings/**").hasAnyRole("CUSTOMER")

                        // Customers
                        .requestMatchers(HttpMethod.GET, "/customer", "/api/customers/**").hasAnyRole("AGENT", "MANAGER")
//                        .requestMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("AGENT", "MANAGER")

                        // Equipments
                        .requestMatchers(HttpMethod.GET, "/equipment", "/api/equipments/**").hasAnyRole("AGENT", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/equipments/**").hasAnyRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/equipments/**").hasAnyRole("MANAGER")

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
                        .requestMatchers(HttpMethod.POST, "/vehicle", "/api/vehicles/**").hasAnyRole("MANAGER", "AGENT")
                        .requestMatchers(HttpMethod.PUT, "/vehicle", "/api/vehicles/**").hasAnyRole("MANAGER")

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