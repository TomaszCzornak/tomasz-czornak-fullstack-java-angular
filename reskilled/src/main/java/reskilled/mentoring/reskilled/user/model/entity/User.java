package reskilled.mentoring.reskilled.user.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String uuid;
    @Column(name = "created_at", nullable = false, updatable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "islock")
    private boolean isLock;

    @Column(name = "isenabled")
    private boolean isEnabled;

    public User() {
        generateUuid();
    }

    public User(String id, String uuid, String createdAt, String updatedAt, String firstName, String lastName, String email, String password, boolean isLock, boolean isEnabled) {
        this.id = id;
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isLock = isLock;
        this.isEnabled = isEnabled;
        generateUuid();
    }

    private void generateUuid() {
        if (uuid==null || uuid.equals("")) {
            setUuid(UUID.randomUUID().toString());
        }
    }
}
