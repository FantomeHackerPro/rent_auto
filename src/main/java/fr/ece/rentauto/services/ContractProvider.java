package fr.ece.rentauto.services;

import fr.ece.rentauto.controllers.dtos.ContractRequestDTO;
import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.ContractEntity;
import fr.ece.rentauto.domaines.mappers.ContractMapper;
import fr.ece.rentauto.repositories.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ContractProvider implements ContractService{

    private ContractRepository contractRepository;

    @Override
    public List<ContractEntity> getContracts() {
        return this.contractRepository.findAll();
    }

    @Override
    public ContractEntity getContract(Long id) throws AppException {
        return this.contractRepository.findById(id).orElseThrow(() -> new AppException("Contract not found.", HttpStatus.NOT_FOUND.value()));
    }
}
