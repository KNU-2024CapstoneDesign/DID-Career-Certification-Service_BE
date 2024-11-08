package did_career_certification.holder.repository;

import did_career_certification.holder.entity.Verifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HolderVerifierRepository")
public interface VerifierRepository extends JpaRepository<Verifier, Long> {

}
