package did_career_certification.holder.repository;

import did_career_certification.holder.entity.Holder;
import did_career_certification.holder.entity.HolderVC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderVCRepository extends JpaRepository<HolderVC, Long> {
    List<HolderVC> findAllByHolder(Holder holder);
}
