package did_career_certification.holder.dto;

import java.util.Date;
import java.util.Map;

public record MyVCResponse(String issuerName, Date issuanceDate,
                           Map<String, Object> credentialSubject) {

    public MyVCResponse(String issuerName, Map<String, Object> vc) {
        this(issuerName, (Date) vc.get("issued"),
            (Map<String, Object>) vc.get("credentialSubject"));
    }
}