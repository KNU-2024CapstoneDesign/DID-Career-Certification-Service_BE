package did_career_certification.holder.dto;

import java.time.LocalDate;
import java.util.Map;

public record VC (String issuerName, LocalDate issueDate, Map<String, String> subject) {

}
