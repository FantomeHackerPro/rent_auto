package fr.ece.rentauto;

import fr.ece.rentauto.services.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentAutoApplication.class, args);
	}

	@Bean
	public ApplicationRunner initData(UserService userService) {
		return args -> userService.initializeManager();
	}

}
