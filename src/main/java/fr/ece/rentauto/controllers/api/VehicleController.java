package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.VehicleRequestDTO;
import fr.ece.rentauto.controllers.dtos.VehicleResponseDTO;
import fr.ece.rentauto.domaines.mappers.VehicleMapper;
import fr.ece.rentauto.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    @GetMapping
    public ResponseMessage<List<VehicleResponseDTO>> getVehicles() throws AppException {
        List<VehicleResponseDTO> vehicles = VehicleMapper.mapToVehicleResponseList(vehicleService.getVehicles());
        int code = !vehicles.isEmpty() ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value();

        ResponseMessage<List<VehicleResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of vehicles");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(vehicles);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<VehicleResponseDTO> getVehicle(@PathVariable Long id) throws AppException {
        VehicleResponseDTO vehicle = VehicleMapper.mapToVehicleResponse(vehicleService.getVehicle(id));
        int code = vehicle != null ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value();

        ResponseMessage<VehicleResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Vehicle");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(vehicle);
        return response;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMessage<?> createVehicle(@ModelAttribute VehicleRequestDTO request) throws AppException {
        vehicleService.addVehicle(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Vehicle has been created successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PostMapping(value = "/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMessage<?> updateVehicle(@PathVariable Long id, @ModelAttribute VehicleRequestDTO request) throws AppException {
        vehicleService.updateVehicle(id, request);
        int code = HttpStatus.OK.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Vehicle has been updated successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }
}