package did_career_certification.holder.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.exception.RequestException;
import did_career_certification.exception.ResponseException;
import did_career_certification.holder.dto.VPRequest;
import did_career_certification.holder.entity.Verifier;
import did_career_certification.holder.repository.VCRepository;
import did_career_certification.holder.repository.VerifierRepository;
import did_career_certification.util.JwtUtil;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service("HolderVPService")
@RequiredArgsConstructor
public class VPService {
    private final RestClient client = RestClient.builder()
        .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
            throw new RequestException("invalid.http.request");
        })
        .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
            throw new ResponseException("not.receive.response");
        })
        .build();
    private final VerifierRepository verifierRepository;
    private final HolderService holderService;
    private final JwtUtil jwtUtil;
    private final VCRepository vcRepository;

    public void submitVP(String walletAddress, VPRequest request) {
        var url = selectVerifier(request.verifierId());
        final var body = createVP(walletAddress, request.vcIds());
        client.post()
            .uri(URI.create(url))
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .retrieve()
            .body(Map.class);
    }

    private String selectVerifier(Long verifierId) {
        Verifier verifier = verifierRepository.findById(verifierId)
            .orElseThrow(() -> new NotFoundException("not.found.verifier"));
        return verifier.getRequestApi();
    }

    private Map<String, Object> createVP(String holderDid, List<Long> vcIds) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("@context", new String[]{"https://www.w3.org/2018/credentials/v1"});
        payload.put("type", new String[]{"VerifiablePresentation"});
        payload.put("holder", holderDid);
        payload.put("verifiableCredential", createVCList(vcIds));
        payload.put("proof", createProof());
        return payload;
    }

    private List<String> createVCList(List<Long> vcIds) {
        List<String> vcList = new ArrayList<>();
        for(Long vcId: vcIds) {
            vcList.add(vcRepository.findById(vcId).orElseThrow().getVcToken());
        }
        return vcList;
    }

    private Map<String, String> createProof() {
        Map<String, String> proof = new HashMap<>();
        proof.put("proofType", "서명타입");
        proof.put("created", String.valueOf(new Date()));
        proof.put("proofpurpose", "assertion");
        proof.put("verificationMethod", "키주소");
        return proof;
    }
}
