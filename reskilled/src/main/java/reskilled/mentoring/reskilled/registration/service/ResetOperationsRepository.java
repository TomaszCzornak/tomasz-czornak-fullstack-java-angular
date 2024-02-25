package reskilled.mentoring.reskilled.registration.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reskilled.mentoring.reskilled.registration.model.entity.ResetOperations;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface ResetOperationsRepository extends JpaRepository<ResetOperations,Long> {

    @Modifying
    void deleteAllByUser(User user);
    Optional<ResetOperations> findByUuid(String uid);
    @Query(nativeQuery = true, value = "SELECT * FROM resetoperations WHERE createdate <= DATEADD('MINUTE', -15, CURRENT_TIMESTAMP)")
    List<ResetOperations> findExpiredOperations();

}
