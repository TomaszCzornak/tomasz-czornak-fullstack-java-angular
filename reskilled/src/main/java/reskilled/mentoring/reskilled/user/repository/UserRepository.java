package reskilled.mentoring.reskilled.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM users where email=?1 and islock=false and isEnabled=true")
    Optional<User> findUserByEmailAndLockAndEnabled(String email);

    Optional<User> findUserById(Long id);
}
