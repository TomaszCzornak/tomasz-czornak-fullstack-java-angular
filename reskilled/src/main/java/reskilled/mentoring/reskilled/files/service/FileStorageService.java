package reskilled.mentoring.reskilled.files.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import reskilled.mentoring.reskilled.files.exceptions.FileStorageException;
import reskilled.mentoring.reskilled.files.model.entity.FileMetadata;
import reskilled.mentoring.reskilled.files.repository.FileRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FileStorageService {

    private final FileRepository fileRepository;

    public String storeFile(MultipartFile file, String candidateId) throws FileStorageException {
        validateFileType(file);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path resourceDirectory = Paths.get("reskilled","src", "main", "resources", "files_uploaded").toAbsolutePath();
            Files.createDirectories(resourceDirectory);
            Path targetLocation = resourceDirectory.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            FileMetadata metadata = FileMetadata.builder()
                    .candidateId(candidateId)
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .build();
            fileRepository.save(metadata);

            return fileName;
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again! " + e.getMessage());
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get("reskilled", "src", "main", "resources", "files_uploaded").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                ClassLoader classLoader = getClass().getClassLoader();
                Resource classPathResource = new ClassPathResource("files_uploaded/" + fileName, classLoader);
                if (classPathResource.exists()) {
                    return classPathResource;
                } else {
                    throw new RuntimeException("File not found: " + fileName);
                }
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found or not readable: " + fileName, ex);

        }}

    private void validateFileType(MultipartFile file) throws FileStorageException {
        if (file.isEmpty()) {
            throw new FileStorageException("Failed to store empty file.");
        }

        if (!Arrays.asList("application/pdf", "application/msword").contains(file.getContentType())) {
            throw new FileStorageException("Only PDF and Word documents are allowed.");
        }

        if (file.getSize() > 5 * 1024 * 1024) { // 5 MB limit
            throw new FileStorageException("File size exceeds the limit of 5 MB.");
        }
    }
}
