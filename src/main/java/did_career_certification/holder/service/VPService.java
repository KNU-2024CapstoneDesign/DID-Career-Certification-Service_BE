package did_career_certification.holder.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.exception.RequestException;
import did_career_certification.exception.ResponseException;
import did_career_certification.global.dto.VC;
import did_career_certification.global.dto.VP;
import did_career_certification.holder.dto.VPRequest;
import did_career_certification.holder.entity.Holder;
import did_career_certification.holder.entity.HolderVC;
import did_career_certification.holder.entity.Verifier;
import did_career_certification.holder.repository.HolderVCRepository;
import did_career_certification.holder.repository.VerifierRepository;
import java.net.URI;
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
    private final HolderVCRepository holderVcRepository;

    public void submitVP(String walletAddress, VPRequest request) {
        System.out.println("request: "+request.verifierId()+", ids: "+request.vcIds().toString());
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

    private VP createVP(String walletAddress, List<Long> vcIds) {
        Holder holder = holderService.findByWalletAddress(walletAddress);
        String holderDid = "did:ethr:" + walletAddress;
        return new VP(
            holder.getName(),
            holderDid,
            createVCList(vcIds)
        );
    }

    private List<VC> createVCList(List<Long> vcIds) {
        List<HolderVC> holderVCList = holderVcRepository.findAllById(vcIds);
        if (holderVCList.size() < vcIds.size()) {
            throw new IllegalArgumentException("Some VC IDs were not found.");
        }
        return holderVCList.stream()
            .map(VC::new)
            .toList();
    }
}
