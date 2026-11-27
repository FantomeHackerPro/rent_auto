package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveVehicleImage(MultipartFile file) throws AppException;
    void deleteVehicleImage(String imageUrl);
}