package did_career_certification.holder.repository;

import did_career_certification.holder.entity.Univ;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnivRepository extends JpaRepository<Univ, Long> {

    Optional<Univ> findByDid(String did);
}
