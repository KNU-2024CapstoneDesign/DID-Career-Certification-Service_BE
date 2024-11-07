package did_career_certification.verifier.service;

import did_career_certification.verifier.dto.VPRequest;
import did_career_certification.verifier.entity.VP;
import did_career_certification.verifier.repository.VCRepository;
import did_career_certification.verifier.repository.VPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VPService {

    private final VPRepository vpRepository;
    private final VCRepository vcRepository;

    public void registerVP(VPRequest request) {
        VP vp = vpRepository.save(request.toEntity());
        vcRepository.saveAll(request.extractVCList(vp));
    }
}
