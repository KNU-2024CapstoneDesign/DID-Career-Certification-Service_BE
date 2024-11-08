package did_career_certification.verifier.repository;

import did_career_certification.verifier.entity.Verifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("VerifierVerifierRepository")
public interface VerifierRepository extends JpaRepository<Verifier, String> {

}
