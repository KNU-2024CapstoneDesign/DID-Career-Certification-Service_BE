package did_career_certification.verifier.repository;

import did_career_certification.verifier.entity.Applicant;
import did_career_certification.verifier.entity.VerifierVC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("VerifierVCRepository")
public interface VCRepository extends JpaRepository<VerifierVC, Long> {

    List<VerifierVC> findAllByApplicant(Applicant applicant);
}
