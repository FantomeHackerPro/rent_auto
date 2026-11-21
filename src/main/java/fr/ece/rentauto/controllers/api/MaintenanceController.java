package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.services.MaintenanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private MaintenanceService maintenanceService;


}
