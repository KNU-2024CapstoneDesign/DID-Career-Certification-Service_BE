package did_career_certification.controller;

import did_career_certification.dto.LoginRequest;
import did_career_certification.dto.RegisterRequest;
import did_career_certification.dto.TokenResponse;
import did_career_certification.service.HolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final HolderService holderService;

    @PostMapping("/holder/register")
    public ResponseEntity<Void> registerHolder(
        @RequestPart(value = "holder") @Valid RegisterRequest request) {
        holderService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/holder/login")
    public ResponseEntity<TokenResponse> loginHolder(@Valid @RequestBody LoginRequest request) {
        TokenResponse response = holderService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
            .body(response);
    }
}
