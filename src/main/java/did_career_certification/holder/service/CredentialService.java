package did_career_certification.holder.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.exception.RequestException;
import did_career_certification.exception.ResponseException;
import did_career_certification.holder.dto.CredentialRequest;
import did_career_certification.holder.dto.IssuerResponse;
import did_career_certification.holder.dto.MyVCResponse;
import did_career_certification.holder.entity.Holder;
import did_career_certification.holder.entity.VC;
import did_career_certification.holder.repository.UnivRepository;
import did_career_certification.holder.repository.VCRepository;
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
    private final UnivRepository univRepository;
    private final JwtUtil jwtUtil;

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
        body.put("stdId", request.stdId());
        return body;
    }

    public IssuerResponse findAllIssuer() {
        return new IssuerResponse(univRepository.findAll());
    }

    public List<MyVCResponse> getMyVc(String walletAddress) {
        Holder holder = holderService.findByWalletAddress(walletAddress);
        List<VC> vcList = vcRepository.findAllByHolder(holder);
        List<MyVCResponse> response = new ArrayList<>();
        for(VC vc: vcList) {
            Map<String, Object> decodeVCToken = jwtUtil.decodeVCToken(vc.getVcToken());
            String issuerName = univRepository.findByDid(decodeVCToken.get("issuerDid").toString())
                .orElseThrow(() -> new NotFoundException("not.found.univ")).getName();
            response.add(new MyVCResponse(vc.getId(), issuerName, decodeVCToken));
        }
        return response;
    }
}