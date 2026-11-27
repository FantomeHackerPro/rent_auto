package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@AllArgsConstructor
@Service
public class FileStorageProvider implements FileStorageService {

    private final Path rootLocation = Paths.get("uploads/vehicles");

    @Override
    public String saveVehicleImage(MultipartFile file) throws AppException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String originalFilename = file.getOriginalFilename();
            String extension = (originalFilename != null && originalFilename.contains("."))
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : ".jpg";

            String uniqueFilename = UUID.randomUUID() + extension;
            Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFilename)).normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return "/uploads/vehicles/" + uniqueFilename;

        } catch (IOException e) {
            throw new AppException("Impossible d'enregistrer l'image du véhicule : " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public void deleteVehicleImage(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            return;
        }
        try {
            String filename = Paths.get(imageUrl).getFileName().toString();
            Path fileToDelete = this.rootLocation.resolve(filename).normalize().toAbsolutePath();
            Files.deleteIfExists(fileToDelete);
        } catch (IOException ignored) {
            // On ignore si le fichier n'existe plus sur le disque
        }
    }
}