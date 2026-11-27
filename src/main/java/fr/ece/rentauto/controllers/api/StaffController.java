package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.StaffRequestDTO;
import fr.ece.rentauto.controllers.dtos.StaffResponseDTO;
import fr.ece.rentauto.domaines.mappers.StaffMapper;
import fr.ece.rentauto.services.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/staffs")
public class StaffController {

    private StaffService staffService;

    @GetMapping
    public ResponseMessage<List<StaffResponseDTO>> getAllStaffs() {
        List<StaffResponseDTO> staffs = StaffMapper.mapToStaffResponseList(staffService.getAllStaff());
        int code = HttpStatus.NOT_FOUND.value();
        if (!staffs.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<StaffResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of agencies");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(staffs);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<StaffResponseDTO> getStaff(@PathVariable Long id) throws AppException {
        StaffResponseDTO staff = StaffMapper.mapToStaffResponse(staffService.getStaff(id));
        int code = HttpStatus.NOT_FOUND.value();
        String message = "Staff not found.";
        if (staff != null) {
            code = HttpStatus.OK.value();
            message = "Staff";
        }
        ResponseMessage<StaffResponseDTO> response = new ResponseMessage<>();
        response.setMessage(message);
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(staff);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> createStaff(@RequestBody StaffRequestDTO request) throws AppException {
        staffService.addStaff(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Staff has been created successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<?> updateStaff(@PathVariable Long id, @RequestBody StaffRequestDTO request) throws AppException {
        staffService.updateStaff(id, request);
        int code = HttpStatus.OK.value();
        ResponseMessage<?> response = new ResponseMessage<>();
        response.setMessage("Staff has been updated successfully.");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

}
