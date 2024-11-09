package did_career_certification.issuer.service;

import did_career_certification.issuer.dto.CredentialSubject;
import did_career_certification.issuer.dto.VC;
import did_career_certification.issuer.dto.VCRequest;
import did_career_certification.issuer.dto.VCResponse;
import did_career_certification.issuer.entity.Student;
import did_career_certification.util.JwtUtil;
import did_career_certification.config.BlockChainConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifiableCredentialService {

    private final BlockChainConfig blockChainConfig;  // 환경설정에서 DID를 읽어옵니다.
    private final StudentService studentService;
    private final JwtUtil jwtUtil;

    /**
     * VC 발급 로직
     */
    public VCResponse issueVC(VCRequest request) {
        // 1. 요청 받은 DID가 유효한지 검증 (예: Holder DID)
        if (request.holderDid() == null || request.holderDid().isEmpty()) {
            throw new IllegalArgumentException("Holder DID는 필수입니다.");
        }

        // 2. 학생 정보 조회
        Student student = studentService.findById(Long.parseLong(request.requireData().get("studentId")));
        if (student == null) {
            throw new IllegalArgumentException("학생 정보가 존재하지 않습니다.");
        }

        // 3. CredentialSubject 생성
        CredentialSubject subject = new CredentialSubject(
            student.getName(),
            student.getCollege(),
            student.getMajor(),
            student.getDegree(),
            student.getAcademicStatus()
        );

        // 4. VC 생성 (Issuer DID와 Holder DID, 그리고 CredentialSubject 포함)
        VC vc = new VC(request.holderDid(), "did:ether:0x2b7BB9a7b7e32fbCE880B91Ad8e0979856a24083", subject);

        // 5. JWT 토큰을 사용하여 VC 생성
        String vcToken = jwtUtil.generateVCToken(vc);

        // 6. VCResponse에 JWT 토큰을 담아서 반환
        return new VCResponse(vcToken);
    }
}
