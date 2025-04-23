package com.izorai.pfa.module1.services.fileStorage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService() {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

        System.out.println("Attempting to create directory at: " + this.fileStorageLocation);

        try {
            Files.createDirectories(this.fileStorageLocation);
            System.out.println("Directory created successfully at: " + this.fileStorageLocation);
        } catch (Exception ex) {
            System.err.println("Directory creation failed:");
            ex.printStackTrace();
            throw new RuntimeException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String subDirectory) throws IOException {
        // Normaliser le nom du fichier
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        System.out.println("Storing file with original name: " + file.getOriginalFilename());


        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + fileExtension;

        // Créer le sous-répertoire s'il n'existe pas
        Path targetLocation = this.fileStorageLocation.resolve(subDirectory);
        Files.createDirectories(targetLocation);

        // Copier le fichier vers l'emplacement cible
        Path filePath = targetLocation.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retourner le chemin relatif (ou absolu selon vos besoins)
        return subDirectory + "/" + fileName;
    }


    public void deleteFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) return;

        Path fileToDelete = this.fileStorageLocation.resolve(filePath);
        Files.deleteIfExists(fileToDelete);
    }

    public String updateFile(MultipartFile newFile, String existingFilePath, String subDirectory) throws IOException {
        if (existingFilePath != null && !existingFilePath.isEmpty()) {
            deleteFile(existingFilePath);
        }
        return storeFile(newFile, subDirectory);
    }

}