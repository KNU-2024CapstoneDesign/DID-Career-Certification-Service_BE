package did_career_certification.holder.repository;

import did_career_certification.holder.entity.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderRepository extends JpaRepository<Holder, String> {

}
