package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.ContractResponseDTO;
import fr.ece.rentauto.domaines.mappers.ContractMapper;
import fr.ece.rentauto.services.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private ContractService contractService;

    @GetMapping
    public ResponseMessage<List<ContractResponseDTO>> getContracts() {
        List<ContractResponseDTO> contracts = ContractMapper.mapToContractResponseList(contractService.getContracts());
        int code = HttpStatus.NOT_FOUND.value();
        if (!contracts.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<ContractResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of contracts");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(contracts);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<ContractResponseDTO> getContract(@PathVariable Long id) throws AppException {
        ContractResponseDTO contract = ContractMapper.mapToContractResponse(contractService.getContract(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (contract != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<ContractResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Contract");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(contract);
        return response;
    }

}
