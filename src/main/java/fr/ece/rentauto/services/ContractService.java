package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.ContractEntity;

import java.util.List;

public interface ContractService {
    List<ContractEntity> getContracts();
    ContractEntity getContract(Long id) throws AppException;
}
