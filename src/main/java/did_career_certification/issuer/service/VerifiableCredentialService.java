package did_career_certification.issuer.service;

import did_career_certification.issuer.dto.CredentialSubject;
import did_career_certification.issuer.dto.VC;
import did_career_certification.issuer.dto.VCRequest;
import did_career_certification.issuer.dto.VCResponse;
import did_career_certification.issuer.entity.Student;
import did_career_certification.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifiableCredentialService {

    private final String ISSUER_DID = "did:ether:0x2b7BB9a7b7e32fbCE880B91Ad8e0979856a24083";
    private final StudentService studentService;
    private final JwtUtil jwtUtil;

    public VCResponse issueVC(VCRequest request) {
        Student student = studentService.findById(request.stdId());
        CredentialSubject subject = new CredentialSubject(student.getName(), student.getCollege(), student.getMajor(), student.getDegree(), student.getAcademicStatus());
        VC vc = new VC(request.holderDid(), ISSUER_DID, subject);
        return new VCResponse(jwtUtil.generateVCToken(vc));
    }
}
