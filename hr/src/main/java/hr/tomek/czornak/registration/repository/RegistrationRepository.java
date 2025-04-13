package hr.tomek.czornak.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.tomek.czornak.user.model.entity.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User, String> {

}
