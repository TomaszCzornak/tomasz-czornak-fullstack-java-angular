package hr.tomek.czornak.files.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import hr.tomek.czornak.files.exceptions.FileStorageException;
import hr.tomek.czornak.files.model.entity.FileMetadata;
import hr.tomek.czornak.files.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileStorageServiceTest {

    @InjectMocks
    private FileStorageService fileStorageService;
    @Mock
    private FileRepository fileRepository;

    @Test
    void storeFile() throws FileStorageException {
        MockMultipartFile file = new MockMultipartFile("test", "test.pdf", "application/pdf", "test content".getBytes());

        String fileName = fileStorageService.storeFile(file, "12345");

        assertNotNull(fileName);
        verify(fileRepository, times(1)).save(any(FileMetadata.class));
    }

    @Test
    void loadFileAsResource() throws IOException {
        String fileName = "test.pdf";
        Path path = Paths.get("tomek/src/main/resources/files_uploaded/test.pdf");

        if (Files.exists(path)) {
            Files.delete(path);
        }

        Files.createFile(path);

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        assertTrue(resource.exists());
        assertTrue(resource.isReadable());

        Files.delete(path);
    }

    @Test
    public void testValidateFileType_ThrowsException_WhenFileIsEmpty() {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", new byte[0]);

        Exception exception = assertThrows(FileStorageException.class, () -> {
            fileStorageService.storeFile(file, "12345");
        });

        String expectedMessage = "Failed to store empty file.";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage, "The exception message should not be null.");
        assertTrue(actualMessage.contains(expectedMessage), "The exception message should contain the expected message.");
    }

    @Test
    public void testValidateFileType_ThrowsException_WhenFileTypeIsInvalid() {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test content".getBytes());
        FileStorageException exception = assertThrows(FileStorageException.class, () -> {
            fileStorageService.storeFile(file, "12345");
        });
        String expectedMessage = "Only PDF and Word documents are allowed.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}