package did_career_certification.verifier.controller;

import did_career_certification.verifier.dto.VPRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verifier")
public class VPController {

    @PostMapping("/vp")
    public ResponseEntity<Void> receiveVP(@RequestBody VPRequest request) {
        System.out.println(request.toString());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}