package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.StaffEntity;
import fr.ece.rentauto.controllers.dtos.StaffRequestDTO;
import fr.ece.rentauto.controllers.dtos.StaffResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaffMapper {
    public static StaffEntity mapToStaffToAdd(StaffRequestDTO request) {
        StaffEntity staff = new StaffEntity();
        staff.setName(request.getName());
        staff.setRole(request.getRole());
        staff.setEmail(request.getEmail());
        staff.setSurname(request.getSurname());
        staff.setCreatedAt(LocalDateTime.now());
        return staff;
    }

    public static StaffEntity mapToStaffToUpdate(StaffEntity staffToUpdate, StaffRequestDTO request) {
        staffToUpdate.setSurname(request.getSurname());
        staffToUpdate.setName(request.getName());
        staffToUpdate.setEmail(request.getEmail());
        staffToUpdate.setRole(request.getRole());
        staffToUpdate.setUpdatedAt(LocalDateTime.now());
        return staffToUpdate;
    }

    public static StaffResponseDTO mapToStaffResponse(StaffEntity staff) {
        if (staff == null) {
            return null;
        }
        StaffResponseDTO response = new StaffResponseDTO();
        response.setId(staff.getId());
        response.setName(staff.getName());
        response.setSurname(staff.getSurname());
        response.setEmail(staff.getEmail());
        response.setRole(staff.getRole());
        response.setAgency(AgencyMapper.mapToAgencySummaryResponse(staff.getAgency()));
        response.setUser(UserMapper.mapToUserSummaryResponse(staff.getUser()));
        return  response;
    }

    public static StaffResponseDTO mapToStaffSummaryResponse(StaffEntity staff) {
        if (staff == null) {
            return null;
        }
        StaffResponseDTO response = new StaffResponseDTO();
        response.setId(staff.getId());
        response.setName(staff.getName());
        response.setEmail(staff.getEmail());
        response.setSurname(staff.getSurname());
        response.setRole(staff.getRole());
        return  response;
    }

    public static List<StaffResponseDTO> mapToStaffResponseList(List<StaffEntity> staff) {
        if (staff.isEmpty()) {
            return new ArrayList<>();
        }
        return staff.stream().map(StaffMapper::mapToStaffResponse).collect(Collectors.toList());
    }

    public static List<StaffResponseDTO> mapToStaffSummaryResponseList(List<StaffEntity> staff) {
        if (staff.isEmpty()) {
            return new ArrayList<>();
        }
        return staff.stream().map(StaffMapper::mapToStaffSummaryResponse).collect(Collectors.toList());
    }
}
