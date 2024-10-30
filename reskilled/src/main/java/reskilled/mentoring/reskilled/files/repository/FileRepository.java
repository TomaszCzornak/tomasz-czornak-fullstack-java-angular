package reskilled.mentoring.reskilled.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reskilled.mentoring.reskilled.files.model.entity.FileMetadata;

public interface FileRepository extends JpaRepository<FileMetadata, Long> {
}