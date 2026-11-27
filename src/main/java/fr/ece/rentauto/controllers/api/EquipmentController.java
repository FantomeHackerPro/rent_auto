package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.EquipmentRequestDTO;
import fr.ece.rentauto.controllers.dtos.EquipmentResponseDTO;
import fr.ece.rentauto.domaines.mappers.EquipmentMapper;
import fr.ece.rentauto.services.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private EquipmentService equipmentService;

    @GetMapping
    public ResponseMessage<List<EquipmentResponseDTO>> getEquipments() {
        List<EquipmentResponseDTO> equipments = EquipmentMapper.mapToEquipmentToResponseList(equipmentService.getEquipments());
        int code = HttpStatus.NOT_FOUND.value();
        if (!equipments.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<EquipmentResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of equipments");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(equipments);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<EquipmentResponseDTO> getEquipment(@PathVariable Long id) throws AppException {
        EquipmentResponseDTO equipment = EquipmentMapper.mapToEquipmentToResponse(equipmentService.getEquipmentById(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (equipment != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<EquipmentResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Equipment");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(equipment);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> createEquipment(@RequestBody EquipmentRequestDTO request) throws AppException {
        equipmentService.addEquipment(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Equipment has been created successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<?> updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequestDTO request) throws AppException {
        equipmentService.updateEquipment(id, request);
        int code = HttpStatus.OK.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Equipment has been updated successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

}
