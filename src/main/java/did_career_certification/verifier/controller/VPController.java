package did_career_certification.verifier.controller;

import did_career_certification.verifier.dto.VCValidateRequest;
import did_career_certification.verifier.dto.VCValidateResponse;
import did_career_certification.verifier.dto.VPRequest;
import did_career_certification.verifier.dto.VPResponse;
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
    public ResponseEntity<Void> receiveVP(@RequestBody VPRequest request) {
        System.out.println("VP받았으: "+request);
        vpService.registerVP(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/vp")
    public ResponseEntity<List<VPResponse>> findAllVP() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(vpService.findAllVP());
    }

    @GetMapping("/vp/{vpId}")
    public ResponseEntity<List<VCValidateResponse>> validateVP(@PathVariable Long vpId) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(vpService.validateVC(vpId));
    }
}