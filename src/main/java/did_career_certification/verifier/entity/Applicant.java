package did_career_certification.verifier.entity;

import did_career_certification.verifier.dto.ApplicantResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String holderDid;

    public Applicant (String holderName, String holderDid) {
        this.holderName = holderName;
        this.holderDid = holderDid;
    }

    public ApplicantResponse toDto() {
        return new ApplicantResponse(id, holderName);
    }
}