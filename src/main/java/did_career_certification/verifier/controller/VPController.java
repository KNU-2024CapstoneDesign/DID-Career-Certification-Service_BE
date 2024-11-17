package did_career_certification.verifier.controller;

import did_career_certification.global.dto.VP;
import did_career_certification.verifier.dto.VCResponse;
import did_career_certification.verifier.dto.VCValidateResponse;
import did_career_certification.verifier.dto.ApplicantResponse;
import did_career_certification.verifier.service.VPService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verifier")
@RequiredArgsConstructor
public class VPController {

    private final VPService vpService;

    @PostMapping("/vp")
    public ResponseEntity<Void> receiveVP(@RequestBody VP request) {
        System.out.println("VP 정상 제출됐습니다.");
        vpService.registerVP(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantResponse>> findAllApplicant() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(vpService.findAllApplicant());
    }

    @GetMapping("/applicants/{applicantId}")
    public ResponseEntity<List<VCResponse>> getCredentials(@PathVariable Long applicantId) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(vpService.findAllVCByApplicant(applicantId));
    }

    @GetMapping("/applicants/{applicantId}/validate")
    public ResponseEntity<List<VCValidateResponse>> validateVP(@PathVariable Long applicantId)
        throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
            .body(vpService.validateVC(applicantId));
    }
}