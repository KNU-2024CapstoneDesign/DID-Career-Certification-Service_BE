package did_career_certification.controller;

import did_career_certification.dto.HolderRegisterRequest;
import did_career_certification.service.HolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final HolderService holderService;

    @PostMapping("/holder/register")
    public ResponseEntity<Void> registerUser(
        @RequestPart(value = "holder") @Valid HolderRegisterRequest request) {
        holderService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
