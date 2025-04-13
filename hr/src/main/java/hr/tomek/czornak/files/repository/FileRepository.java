package hr.tomek.czornak.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.tomek.czornak.files.model.entity.FileMetadata;

public interface FileRepository extends JpaRepository<FileMetadata, Long> {
}