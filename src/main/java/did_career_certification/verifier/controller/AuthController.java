package did_career_certification.verifier.controller;

import did_career_certification.verifier.dto.LoginRequest;
import did_career_certification.verifier.dto.RegisterRequest;
import did_career_certification.verifier.dto.TokenResponse;
import did_career_certification.verifier.service.VerifierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Component("VerifierAuthController")
public class AuthController {

    private final VerifierService verifierService;

    @PostMapping("/verifier/register")
    public ResponseEntity<Void> registerHolder(@Valid @RequestBody RegisterRequest request) {
        verifierService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/verifier/login")
    public ResponseEntity<TokenResponse> loginHolder(@Valid @RequestBody LoginRequest request) {
        TokenResponse response = verifierService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
            .body(response);
    }
}