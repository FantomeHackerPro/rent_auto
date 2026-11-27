package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.MaintenanceRequestDTO;
import fr.ece.rentauto.controllers.dtos.MaintenanceResponseDTO;
import fr.ece.rentauto.domaines.mappers.MaintenanceMapper;
import fr.ece.rentauto.services.MaintenanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private MaintenanceService maintenanceService;

    @GetMapping
    public ResponseMessage<List<MaintenanceResponseDTO>> getMaintenances() {
        List<MaintenanceResponseDTO> maintenances = MaintenanceMapper.mapToMaintenanceResponseList(maintenanceService.getMaintenances());
        int code = HttpStatus.NOT_FOUND.value();
        if (!maintenances.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<MaintenanceResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of maintenances");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(maintenances);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<MaintenanceResponseDTO> getMaintenance(@PathVariable Long id) throws AppException {
        MaintenanceResponseDTO maintenance = MaintenanceMapper.mapToMaintenanceResponse(maintenanceService.getMaintenanceById(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (maintenance != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<MaintenanceResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Maintenance");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(maintenance);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> createMaintenance(@RequestBody MaintenanceRequestDTO request) throws AppException {
        maintenanceService.addMaintenance(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Maintenance has been created successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<?> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceRequestDTO request) throws AppException {
        maintenanceService.updateMaintenance(id, request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Maintenance has been updated successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @DeleteMapping("/{id}/delete")
    public ResponseMessage<?> deleteMaintenance(@PathVariable Long id) throws AppException {
        maintenanceService.deleteMaintenance(id);
        int code = HttpStatus.OK.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Maintenance has been deleted successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

}
