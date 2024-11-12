package did_career_certification.issuer.service;

import did_career_certification.global.DIDService;
import did_career_certification.global.IPFSService;
import did_career_certification.issuer.dto.VC;
import did_career_certification.issuer.dto.VCRequest;
import did_career_certification.issuer.dto.VCResponse;
import did_career_certification.issuer.entity.Student;
import did_career_certification.util.JwtUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifiableCredentialService {

    private final StudentService studentService;
    private final JwtUtil jwtUtil;
    private final DIDService didService;
    private final IPFSService ipfsService;

    private final String ISSUER_DID_ADDRESS = "did:ether:0x6bB28a619f715461281e8bF999B9d5Cace151333";

    /**
     * VC 발급 로직
     */
    public VCResponse issueVC(VCRequest request) throws Exception {
        // 1. 요청 받은 DID가 유효한지 검증 (예: Holder DID)
        if (request.holderDid() == null || request.holderDid().isEmpty()) {
            throw new IllegalArgumentException("Holder DID는 필수입니다.");
        }

        // 2. 학생 정보 조회
        Student student = studentService.findById(Long.parseLong(request.requireData().get("studentId")));

        // 3. 증명서 발급
        Map<String, String> certificate = student.createCertificate();

        // 4. 증명서 암호화
        String token = jwtUtil.encryptCertificate(certificate);

        // 5. IPFS 저장
        String ipfsHash = ipfsService.saveCertificate(token);

        // 6. IPFS 저장소 해시값 블록체인에 저장
        didService.storeDID(ISSUER_DID_ADDRESS, ipfsHash);

        // 7. VC 생성 (Issuer DID와 Holder DID, 암호화된 증명서)
        VC vc = new VC(request.holderDid(), ISSUER_DID_ADDRESS, token);

        // 8. JWT 토큰을 사용하여 VC 암호화
        String vcToken = jwtUtil.generateVCToken(vc);

        // 9. 암호화한 VC 반환
        return new VCResponse(vcToken);
    }
}
