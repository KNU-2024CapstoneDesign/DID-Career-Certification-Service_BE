package did_career_certification.verifier.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.global.DIDService;
import did_career_certification.global.dto.VP;
import did_career_certification.jwt.CertificateJwt;
import did_career_certification.verifier.dto.VCResponse;
import did_career_certification.verifier.dto.VCValidateResponse;
import did_career_certification.verifier.dto.ApplicantResponse;
import did_career_certification.verifier.entity.VerifierVC;
import did_career_certification.verifier.entity.Applicant;
import did_career_certification.verifier.repository.VCRepository;
import did_career_certification.verifier.repository.ApplicantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("VerifierVPService")
@RequiredArgsConstructor
public class VPService {

    private final ApplicantRepository applicantRepository;
    private final VCRepository vcRepository;
    private final CertificateJwt certificateJwt;
    private final DIDService didService;

    public void registerVP(VP vp) {
        Applicant applicant = applicantRepository.save(vp.toApplicantEntity());
        vcRepository.saveAll(vp.toVerifierVCEntityList(applicant));
    }

    public List<ApplicantResponse> findAllApplicant() {
        return applicantRepository.findAll().stream()
            .map(Applicant::toDto)
            .collect(Collectors.toList());
    }

    public List<VCResponse> findAllVCByApplicant(Long applicantId) {
        Applicant applicant = findById(applicantId);
        List<VCResponse> vcList = new ArrayList<>();
        for(VerifierVC verifierVc : vcRepository.findAllByApplicant(applicant)) {
            Map<String, String> certificate = certificateJwt.parseUnivTokenToMap(verifierVc.getVcToken());
            vcList.add(new VCResponse(
                verifierVc.getId(),
                verifierVc.getIssuerName(),
                certificate.get("course"),
                certificate.get("academicStatus")
            ));
        }
        return vcList;
    }

    public List<VCValidateResponse> validateVC(Long applicantId) throws Exception {
        Applicant applicant = findById(applicantId);
        List<VCValidateResponse> response = new ArrayList<>();
        for(VerifierVC verifierVc : vcRepository.findAllByApplicant(applicant)) {
            String token = didService.getCertificate(verifierVc.getIssuerAccount(), verifierVc.getDocumentId());
            Map<String, String> parseToken = certificateJwt.parseUnivTokenToMap(token);
            response.add(new VCValidateResponse(
                verifierVc.getIssuerName(),
                parseToken.get("course"),
                parseToken.get("academicStatus"),
                verifierVc.getVcToken().equals(token)));
        }
        return response;
    }

    private Applicant findById(Long id) {
        return applicantRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("not.found.applicant"));
    }
}