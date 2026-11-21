package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.AgencyRequestDTO;
import fr.ece.rentauto.controllers.dtos.AgencyResponseDTO;
import fr.ece.rentauto.domaines.mappers.AgencyMapper;
import fr.ece.rentauto.services.AgencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    private AgencyService agencyService;

    @GetMapping
    public ResponseMessage<List<AgencyResponseDTO>> getAgencies() {
        List<AgencyResponseDTO> agencies = AgencyMapper.mapToAgencyResponseList(agencyService.getAgencies());
        int code = HttpStatus.NOT_FOUND.value();
        if (!agencies.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<AgencyResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of agencies");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(agencies);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<AgencyResponseDTO> getAgency(@PathVariable Long id) throws AppException {
        AgencyResponseDTO agency = AgencyMapper.mapToAgencyResponse(agencyService.getAgency(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (agency != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<AgencyResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Agency");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(agency);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> addAgency(@RequestBody AgencyRequestDTO request) throws AppException {
        agencyService.addAgency(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<AgencyResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Agency has been created successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("{id}/update")
    public ResponseMessage<?> updateAgency(@PathVariable Long id, @RequestBody AgencyRequestDTO request) throws AppException {
        agencyService.updateAgency(id, request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<AgencyResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Agency has been updated successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }
}
