package did_career_certification.issuer.controller;

import did_career_certification.issuer.dto.VCRequest;
import did_career_certification.issuer.dto.VCResponse;
import did_career_certification.issuer.service.VerifiableCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issuer/vc")
@RequiredArgsConstructor
public class VerifiableCredentialController {

    private final VerifiableCredentialService verifiableCredentialService;

    @PostMapping
    public ResponseEntity<VCResponse> issueVC(@RequestBody VCRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
            .body(verifiableCredentialService.issueVC(request));
    }
}
