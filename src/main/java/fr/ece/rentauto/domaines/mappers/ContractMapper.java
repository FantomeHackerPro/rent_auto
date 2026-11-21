package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.ContractEntity;
import fr.ece.rentauto.controllers.dtos.ContractRequestDTO;
import fr.ece.rentauto.controllers.dtos.ContractResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContractMapper {
    public static ContractEntity mapToContractToAdd(ContractRequestDTO request) {
        ContractEntity contract = new ContractEntity();
        contract.setPrice(request.getPrice());
        contract.setSignatureDate(request.getSignatureDate());
        contract.setValidity(true);
        contract.setCreatedAt(LocalDateTime.now());
        return contract;
    }

    public static ContractEntity mapToContractToUpdate(ContractEntity contractToUpdate, ContractRequestDTO request) {
        contractToUpdate.setPrice(request.getPrice());
        contractToUpdate.setSignatureDate(request.getSignatureDate());
        contractToUpdate.setValidity(request.isValidity());
        contractToUpdate.setUpdatedAt(LocalDateTime.now());
        return contractToUpdate;
    }

    public static ContractResponseDTO mapToContractResponse(ContractEntity contractEntity) {
        ContractResponseDTO response = new ContractResponseDTO();
        response.setId(contractEntity.getId());
        response.setPrice(contractEntity.getPrice());
        response.setSignatureDate(contractEntity.getSignatureDate());
        response.setValidity(contractEntity.isValidity());
        response.setBooking(BookingMapper.mapToBookingSummaryResponse(contractEntity.getBooking()));
        response.setPayments(PaymentMapper.mapToPaymentSummaryResponseList(contractEntity.getPayments()));
        return  response;
    }

    public static ContractResponseDTO mapToContractSummaryResponse(ContractEntity contractEntity) {
        ContractResponseDTO response = new ContractResponseDTO();
        response.setId(contractEntity.getId());
        response.setPrice(contractEntity.getPrice());
        response.setSignatureDate(contractEntity.getSignatureDate());
        response.setValidity(contractEntity.isValidity());
        return  response;
    }

    public static List<ContractResponseDTO> mapToContractResponseList(List<ContractEntity> contracts) {
        if (contracts.isEmpty()) {
            return new ArrayList<>();
        }
        return contracts.stream().map(ContractMapper::mapToContractResponse).collect(Collectors.toList());
    }
}
