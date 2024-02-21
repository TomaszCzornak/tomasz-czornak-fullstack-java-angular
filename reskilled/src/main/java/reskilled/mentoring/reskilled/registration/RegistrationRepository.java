package reskilled.mentoring.reskilled.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reskilled.mentoring.reskilled.user.model.entity.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User, String> {

}
