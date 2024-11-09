package did_career_certification.holder.repository;

import did_career_certification.holder.entity.Issuer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuerRepository extends JpaRepository<Issuer, Long> {

    Optional<Issuer> findByDid(String did);
}
