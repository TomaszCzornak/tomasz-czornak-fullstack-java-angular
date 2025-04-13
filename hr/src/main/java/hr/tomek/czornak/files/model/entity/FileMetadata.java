package hr.tomek.czornak.files.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="file_meta_data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candidateId;
    private String fileName;
    private String fileType;
    private long fileSize;
}
