package did_career_certification.holder.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.global.dto.KangwonUnivCertificate;
import did_career_certification.holder.dto.UpdateVCRequest;
import did_career_certification.holder.dto.UpdateVCResponse;
import did_career_certification.holder.entity.HolderVC;
import did_career_certification.holder.repository.HolderVCRepository;
import did_career_certification.jwt.CertificateJwt;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VCService {

    private final CertificateJwt certificateJwt;
    private final HolderVCRepository holderVCRepository;

    public List<UpdateVCResponse> findAll() {
        return holderVCRepository.findAll().stream()
            .map(vc -> vc.toUpdateDto(certificateJwt))
            .toList();
    }

    public UpdateVCResponse getVC(Long id) {
        return findById(id).toUpdateDto(certificateJwt);
    }

    public void updateVC(Long id, UpdateVCRequest request) {
        HolderVC vc = findById(id);
        String updateToken = certificateJwt.encryptCertificate(new KangwonUnivCertificate(
            "did:ether:"+vc.getHolder().getWalletAddress(),
            vc.getId(),
            vc.getHolder().getName(),
            request.course(),
            request.academicStatus()
        ));
        holderVCRepository.save(new HolderVC(
            vc.getId(),
            vc.getHolder(),
            vc.getIssuerName(),
            vc.getIssuerAccount(),
            vc.getDocumentId(),
            updateToken,
            vc.getIssuanceDate(),
            vc.getExpirationDate()
        ));
    }

    private HolderVC findById(Long id) {
        return holderVCRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("not.found.vc"));
    }
}
