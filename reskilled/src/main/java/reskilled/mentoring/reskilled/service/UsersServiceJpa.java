package reskilled.mentoring.reskilled.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reskilled.mentoring.reskilled.model.User;

import java.util.Optional;

@Repository
public interface UsersServiceJpa extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);
}
