package did_career_certification.holder.dto;

import java.util.Map;

public record MyVCResponse(Long id, String issuerName, String issuanceDate, Map<String, String> credentialSubject) {

}