package did_career_certification.verifier.entity;

import did_career_certification.global.dto.VC;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigInteger;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "VerifierVC")
@Table(name = "VERIFIER_VC_TB")
@NoArgsConstructor
@Getter
public class VerifierVC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Applicant applicant;

    private BigInteger documentId;

    private String issuerName;

    private String issuerAccount;

    @Column(nullable = false, length = 1024)
    private String vcToken;

    public VerifierVC(Applicant applicant, VC vc) {
        this.applicant = applicant;
        this.documentId = vc.documentId();
        this.issuerName = vc.issuerName();
        this.issuerAccount = vc.issuerAccount();
        this.vcToken = vc.certificateToken();
    }
}
