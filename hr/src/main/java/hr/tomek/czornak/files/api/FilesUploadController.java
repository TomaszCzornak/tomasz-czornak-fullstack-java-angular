package hr.tomek.czornak.files.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import hr.tomek.czornak.files.exceptions.FileStorageException;
import hr.tomek.czornak.files.service.FileStorageService;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/files")
@Slf4j
public class FilesUploadController {

    private final FileStorageService fileStorageService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = "application/json")
    @Operation(summary = "Upload a file", description = "This endpoint is for uploading a file", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "File added successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("candidateId")
    String candidateId) throws FileStorageException {
        String fileName = fileStorageService.storeFile(file, candidateId);
        return ResponseEntity.ok("File uploaded successfully " + fileName);
    }

    @Operation(summary = "Download a file", description = "This endpoint is downloading previously uploaded files", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "File downloaded successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request due to validation failure")})
    @GetMapping(value = "/{fileName}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource;
        try {
            resource = fileStorageService.loadFileAsResource(fileName);
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        String contentType;
        try {
            contentType = Files.probeContentType(resource.getFile().toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
