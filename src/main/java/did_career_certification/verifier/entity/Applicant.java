package did_career_certification.verifier.entity;

import did_career_certification.verifier.dto.ApplicantResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    private String holderAccount;

    private String profileImageUrl;

    private LocalDateTime submitTime;

    public Applicant(String holderName, String holderAccount, String profileImageUrl,
        LocalDateTime submitTime) {
        this.holderName = holderName;
        this.holderAccount = holderAccount;
        this.profileImageUrl = profileImageUrl;
        this.submitTime = submitTime;
    }

    public ApplicantResponse toDto() {
        return new ApplicantResponse(id, holderName, profileImageUrl, submitTime);
    }
}