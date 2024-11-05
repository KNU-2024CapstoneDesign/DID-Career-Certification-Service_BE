package did_career_certification.holder.controller;

import did_career_certification.holder.annotation.Login;
import did_career_certification.holder.dto.CredentialRequest;
import did_career_certification.holder.dto.IssuerResponse;
import did_career_certification.holder.dto.MyVCResponse;
import did_career_certification.holder.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @GetMapping("/issuers")
    public ResponseEntity<IssuerResponse> findAllIssuer() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(credentialService.findAllIssuer());
    }

    @PostMapping("/request")
    public ResponseEntity<Void> reqeustIssueCredential(@Login String walletAddress,
        @RequestBody CredentialRequest request) {
        credentialService.requestIssueCredential(walletAddress, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<MyVCResponse> getMyVc(@Login String walletAddress) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(credentialService.getMyVc(walletAddress));
    }
}
