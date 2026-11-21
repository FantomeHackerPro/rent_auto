package fr.ece.rentauto.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails user = User.withUsername("student")
//                .password(encoder.encode("student123"))
//                .roles("USER").build();
//        UserDetails admin = User.withUsername("admin")
//                .password(encoder.encode("admin123"))
//                .roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**", "/assets/**", "/agency", "/customer", "/equipment", "/payment", "/staff", "/booking", "/user", "/vehicle", "/**").permitAll()
//                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
                // Indique l'URL de votre page HTML de login (ex: login.html dans templates)
                .loginPage("/login")
                // URL traitée par Spring Security pour soumettre le formulaire
                .loginProcessingUrl("/login")
                // Page par défaut après une connexion réussie
                .defaultSuccessUrl("/dashboard", true) // Modifiez selon votre page d'accueil
                .permitAll()
        )
        .logout(logout -> logout
                // URL pour déclencher la déconnexion
                .logoutUrl("/logout")
                // Redirection après une déconnexion réussie (avec le paramètre ?logout)
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );

        return http.build();
    }
}