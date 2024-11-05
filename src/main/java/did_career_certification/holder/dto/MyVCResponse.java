package did_career_certification.holder.dto;

import java.util.Date;
import java.util.Map;

public record MyVCResponse(Long id, String issuerName, Date issuanceDate,
                           Map<String, Object> credentialSubject) {

    public MyVCResponse(Long id, String issuerName, Map<String, Object> vc) {
        this(id, issuerName, (Date) vc.get("issued"),
            (Map<String, Object>) vc.get("credentialSubject"));
    }
}