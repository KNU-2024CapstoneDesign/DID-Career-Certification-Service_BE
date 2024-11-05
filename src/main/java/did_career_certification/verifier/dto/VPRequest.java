package did_career_certification.verifier.dto;

import java.util.List;
import java.util.Map;

public record VPRequest(String[] context, String[] type, String holder,
                        List<Map<String, Object>> verifiableCredential, Map<String, Object> proof) {

}