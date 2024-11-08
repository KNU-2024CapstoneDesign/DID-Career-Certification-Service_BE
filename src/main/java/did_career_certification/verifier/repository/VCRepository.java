package did_career_certification.verifier.repository;

import did_career_certification.verifier.entity.VC;
import did_career_certification.verifier.entity.VP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("VerifierVCRepository")
public interface VCRepository extends JpaRepository<VC, Long> {

    List<VC> findAllByVp(VP vp);
}
