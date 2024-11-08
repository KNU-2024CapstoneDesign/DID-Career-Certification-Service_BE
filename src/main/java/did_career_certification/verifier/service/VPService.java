package did_career_certification.verifier.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.verifier.dto.VCValidateRequest;
import did_career_certification.verifier.dto.VCValidateResponse;
import did_career_certification.verifier.dto.VPRequest;
import did_career_certification.verifier.dto.VPResponse;
import did_career_certification.verifier.entity.VC;
import did_career_certification.verifier.entity.VP;
import did_career_certification.verifier.repository.VCRepository;
import did_career_certification.verifier.repository.VPRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VPService {

    private final VPRepository vpRepository;
    private final VCRepository vcRepository;

    public void registerVP(VPRequest request) {
        VP vp = vpRepository.save(request.toEntity());
        List<VC> vc = vcRepository.saveAll(request.extractVCList(vp));
    }

    public List<VPResponse> findAllVP() {
        return vpRepository.findAll().stream()
            .map(vp -> vp.toDto(vcRepository.findAllByVp(vp)))
            .collect(Collectors.toList());
    }

    public VCValidateResponse validateVC(Long id) {
        VP vp = vpRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("not.found.vp"));
        List<VC> vcList = vcRepository.findAllByVp(vp);
        List<Long> response = new ArrayList<>();
        for(VC vc: vcList) {
            response.add(vc.getId());
        }
        return new VCValidateResponse(response);
    }
}
