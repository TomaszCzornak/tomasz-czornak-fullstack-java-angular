package reskilled.mentoring.reskilled.domain.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reskilled.mentoring.reskilled.domain.model.entity.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User, String> {

}
