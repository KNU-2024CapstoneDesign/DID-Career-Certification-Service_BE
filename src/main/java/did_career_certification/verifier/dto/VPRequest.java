package did_career_certification.verifier.dto;

import java.util.List;

public record VPRequest(String holderName, String holderDid, List<String> vcTokenList) {


}