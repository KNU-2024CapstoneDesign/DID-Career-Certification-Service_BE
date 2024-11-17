package did_career_certification.verifier.repository;

import did_career_certification.verifier.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}
