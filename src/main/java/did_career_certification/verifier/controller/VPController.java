package did_career_certification.verifier.controller;

import did_career_certification.verifier.dto.VPRequest;
import did_career_certification.verifier.service.VPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> receiveVP(@RequestBody VPRequest request) {
        vpService.registerVP(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}