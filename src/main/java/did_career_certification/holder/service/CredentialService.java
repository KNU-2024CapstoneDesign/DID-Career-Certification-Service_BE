package did_career_certification.holder.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.exception.RequestException;
import did_career_certification.exception.ResponseException;
import did_career_certification.holder.dto.CredentialRequest;
import did_career_certification.holder.dto.IssuerResponse;
import did_career_certification.holder.dto.MyVCResponse;
import did_career_certification.holder.dto.VPRequest;
import did_career_certification.holder.dto.VerifierResponse;
import did_career_certification.holder.entity.Holder;
import did_career_certification.holder.entity.Issuer;
import did_career_certification.holder.entity.VC;
import did_career_certification.holder.entity.Verifier;
import did_career_certification.holder.repository.IssuerRepository;
import did_career_certification.holder.repository.VCRepository;
import did_career_certification.holder.repository.VerifierRepository;
import did_career_certification.util.JwtUtil;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.HttpStatusCode;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final HolderService holderService;
    private final VCRepository vcRepository;

    private final RestClient client = RestClient.builder()
        .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
            throw new RequestException("invalid.http.request");
        })
        .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
            throw new ResponseException("not.receive.response");
        })
        .build();
    private final IssuerRepository issuerRepository;
    private final JwtUtil jwtUtil;
    private final VerifierRepository verifierRepository;

    public void requestIssueCredential(String walletAddress, CredentialRequest request) {
        var url = "http://localhost:8080/api/issuer/vc";
        Holder holder = holderService.findByWalletAddress(walletAddress);
        final var body = createBody(request, holder.getName());
        var response = client.post()
            .uri(URI.create(url))
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .retrieve()
            .body(Map.class);
        if(response == null)
            throw new ResponseException("not.receive.response");
        vcRepository.save(new VC(holder, response.get("vcToken").toString()));
    }

    private Map<String, Object> createBody(CredentialRequest request, String holderName) {
        Map<String, Object> body = new HashMap<>();
        body.put("holderDid", request.holderDid());
        body.put("name", holderName);
        body.put("requireData", request.requireData());
        return body;
    }

    public IssuerResponse findAllIssuer() {
        return new IssuerResponse(issuerRepository.findAll().stream()
            .map(Issuer::toDto)
            .toList());
    }

    public List<MyVCResponse> getMyVc(String walletAddress) {
        Holder holder = holderService.findByWalletAddress(walletAddress);
        List<VC> vcList = vcRepository.findAllByHolder(holder);
        List<MyVCResponse> response = new ArrayList<>();
        for(VC vc: vcList) {
            Map<String, Object> decodeVCToken = jwtUtil.decodeVCToken(vc.getVcToken());
            System.out.println(decodeVCToken.get("issuerDid"));
            String issuerName = issuerRepository.findByDid(decodeVCToken.get("issuerDid").toString())
                .orElseThrow(() -> new NotFoundException("not.found.univ")).getName();
            response.add(new MyVCResponse(vc.getId(), issuerName, decodeVCToken));
        }
        return response;
    }

    public void submitVP(String walletAddress, VPRequest request) {
        Verifier verifier = verifierRepository.findById(request.verifierId())
            .orElseThrow(() -> new NotFoundException("not.found.verifier"));
        Holder holder = holderService.findByWalletAddress(walletAddress);
        List<Map<String, Object>> vcList = new ArrayList<>();
        for(Long vcId: request.vcIds()) {
             vcList.add(jwtUtil.decodeVCToken(vcRepository.findById(vcId).orElseThrow()
                .getVcToken()));
        }
        var url = "http://localhost:8080/api/verifier/vp";
        final var body = createVP(holder.getWalletAddress(), vcList);
        client.post()
            .uri(URI.create(url))
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .retrieve()
            .body(Map.class);
    }

    private Map<String, Object> createVP(String holderDid, List<Map<String, Object>> vcList) {
        Map<String, Object> proof = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        payload.put("@context", new String[]{"https://www.w3.org/2018/credentials/v1"});
        payload.put("type", new String[]{"VerifiablePresentation"});
        payload.put("holder", holderDid);
        payload.put("verifiableCredential", vcList);
        payload.put("proof", proof);
        return payload;
    }

    public List<VerifierResponse> findAllVerifier() {
        return verifierRepository.findAll().stream()
            .map(Verifier::toDto)
            .toList();
    }
}