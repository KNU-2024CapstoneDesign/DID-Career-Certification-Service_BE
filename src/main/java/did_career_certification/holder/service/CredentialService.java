package did_career_certification.holder.service;

import did_career_certification.exception.RequestException;
import did_career_certification.exception.ResponseException;
import did_career_certification.holder.dto.CredentialRequest;
import did_career_certification.holder.entity.Holder;
import java.net.URI;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.http.HttpStatusCode;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final HolderService holderService;

    private final RestClient client = RestClient.builder()
        .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
            throw new RequestException("invalid.http.request");
        })
        .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
            throw new ResponseException("not.receive.response");
        })
        .build();

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
    }

    private LinkedMultiValueMap<String, Object> createBody(CredentialRequest request,
        String holderName) {
        var body = new LinkedMultiValueMap<String, Object>();
        body.add("holderDid", request.did());
        body.add("name", holderName);
        body.add("stdId", request.stdId());
        return body;
    }
}
