package did_career_certification.holder.dto;

import java.util.Map;

public record HolderVC(String issuerName, String issuerAccount, String issueDate, Map<String, String> subject) {

}
