package did_career_certification.holder.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record MyVCResponse(Long id, String issuerName, LocalDateTime issuanceDate,
                           LocalDateTime expirationDate, Map<String, String> credentialSubject) {

}