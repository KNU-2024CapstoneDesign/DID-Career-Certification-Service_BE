package did_career_certification.issuer.dto;

import java.util.Map;

public record VCRequest (String holderDid, String name, Map<String, String> requireData) {

}
