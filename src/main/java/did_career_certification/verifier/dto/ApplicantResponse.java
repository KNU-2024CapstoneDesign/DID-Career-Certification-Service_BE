package did_career_certification.verifier.dto;

import java.time.LocalDateTime;

public record ApplicantResponse(Long id, String name, String profileImageUrl, LocalDateTime submitTime) {

}