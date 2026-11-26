package fr.ece.rentauto.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
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

    // In-memory accounts for the practical work only. Passwords can be overridden with the
    // APP_STUDENT_PASSWORD / APP_ADMIN_PASSWORD environment variables so they are not the only
    // source of truth in the code; the defaults are the values asked by the exercise.
    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder encoder,
            @Value("${APP_STUDENT_PASSWORD:student123}") String studentPassword,
            @Value("${APP_ADMIN_PASSWORD:admin123}") String adminPassword) {
        UserDetails user = User.withUsername("student")
                .password(encoder.encode(studentPassword))
                .roles("USER").build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode(adminPassword))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Chain 1: REST API protected by HTTP Basic (401 when not authenticated, 403 when the role is not enough).
    // Rules are evaluated in order: the most specific matchers must come first.
    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Chain 2: web pages with form login. Deny by default: only the login page, static
    // resources and the monitoring endpoints are public.
    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error", "/assets/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
                        .requestMatchers("/actuator/health", "/actuator/prometheus").permitAll()
                        // Assumption: staff and user management are reserved to administrators.
                        .requestMatchers("/staff/**", "/user/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }
}
