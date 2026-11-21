package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.AgencyEntity;
import fr.ece.rentauto.controllers.dtos.AgencyRequestDTO;
import fr.ece.rentauto.controllers.dtos.AgencyResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgencyMapper {
    public static AgencyEntity mapToAgencyToAdd (AgencyRequestDTO request) {
        AgencyEntity agency = new AgencyEntity();
        agency.setAddress(request.getAddress());
        agency.setCity(request.getCity());
        agency.setEmail(request.getEmail());
        agency.setPhone(request.getPhone());
        agency.setName(request.getName());
        agency.setCreatedAt(LocalDateTime.now());
        return agency;
    }

    public static AgencyEntity mapToAgencyToUpdate(AgencyEntity agencyToUpdate, AgencyRequestDTO request) {
        agencyToUpdate.setAddress(request.getAddress());
        agencyToUpdate.setCity(request.getCity());
        agencyToUpdate.setEmail(request.getEmail());
        agencyToUpdate.setPhone(request.getPhone());
        agencyToUpdate.setName(request.getName());
        agencyToUpdate.setUpdatedAt(LocalDateTime.now());
        return agencyToUpdate;
    }

    public static AgencyResponseDTO mapToAgencyResponse(AgencyEntity agency) {
        AgencyResponseDTO response = new AgencyResponseDTO();
        response.setId(agency.getId());
        response.setAddress(agency.getAddress());
        response.setCity(agency.getCity());
        response.setEmail(agency.getEmail());
        response.setPhone(agency.getPhone());
        response.setName(agency.getName());
        response.setStaffs(StaffMapper.mapToStaffSummaryResponseList(agency.getStaffs()));
        response.setVehicles(VehicleMapper.mapToVehicleSummaryResponseList(agency.getVehicles()));
        return response;
    }

    public static AgencyResponseDTO mapToAgencySummaryResponse(AgencyEntity agency) {
        if (agency == null) {
            return null;
        }
        AgencyResponseDTO response = new AgencyResponseDTO();
        response.setId(agency.getId());
        response.setAddress(agency.getAddress());
        response.setCity(agency.getCity());
        response.setEmail(agency.getEmail());
        response.setPhone(agency.getPhone());
        response.setName(agency.getName());
        return response;
    }

    public static List<AgencyResponseDTO> mapToAgencyResponseList(List<AgencyEntity> agencies) {
        if (agencies.isEmpty()) {
            return new ArrayList<>();
        }
        return agencies.stream().map(AgencyMapper::mapToAgencyResponse).collect(Collectors.toList());
    }
}
