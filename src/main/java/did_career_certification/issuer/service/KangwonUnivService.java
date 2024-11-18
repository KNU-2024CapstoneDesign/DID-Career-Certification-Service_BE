package did_career_certification.issuer.service;

import did_career_certification.global.DIDService;
import did_career_certification.global.dto.KangwonUnivCertificate;
import did_career_certification.global.dto.VC;
import did_career_certification.issuer.dto.VCRequest;
import did_career_certification.issuer.entity.Student;
import did_career_certification.jwt.CertificateJwt;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KangwonUnivService {

    private final StudentService studentService;
    private final DIDService didService;

    private final String ISSUER_ACCOUNT = "0x653D13207B1b3FdFB85f843FF3e7bfFC9BbC22D7";
    private final CertificateJwt certificateJwt;
    private final static String KANGWON_UNIV_CHUNCHON = "강원대학교(춘천)";

    public VC issueVC(VCRequest request) throws Exception {
        // 1. 요청 받은 DID가 유효한지 검증 (예: Holder DID)
        if (request.holderDid() == null || request.holderDid().isEmpty()) {
            throw new IllegalArgumentException("Holder DID는 필수입니다.");
        }

        // 2. 학생 정보 조회
        Student student = studentService.findById(Long.parseLong(request.requireData().get("studentId")));

        // 3. 증명서 발급
        KangwonUnivCertificate certificate = student.createCertificate(request.holderDid());

        // 4. 증명서 암호화
        String certificateToken = certificateJwt.encryptCertificate(certificate);

        // 5. 암호화된 증명서 블록체인에 저장
        BigInteger documentId = didService.storeCertificate(certificateToken);

        // 6. VC 생성 (Issuer DID와 Holder DID, 암호화된 증명서) 및 반환
        LocalDateTime today = LocalDateTime.now();
        return new VC(KANGWON_UNIV_CHUNCHON, ISSUER_ACCOUNT, today, today.plusMonths(1), documentId, certificateToken);
    }
}
