package hr.tomek.czornak.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.tomek.czornak.recruitment.entity.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

}
