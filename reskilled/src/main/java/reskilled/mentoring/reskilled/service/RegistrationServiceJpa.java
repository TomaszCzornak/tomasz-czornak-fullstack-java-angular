package reskilled.mentoring.reskilled.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reskilled.mentoring.reskilled.model.User;

@Repository
public interface RegistrationServiceJpa extends JpaRepository<User, String> {

}
