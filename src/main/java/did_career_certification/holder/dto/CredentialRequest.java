package did_career_certification.holder.dto;

import java.util.Map;

public record CredentialRequest(String holderDid, Long issuerId, Map<String, String> requireData) {

}
