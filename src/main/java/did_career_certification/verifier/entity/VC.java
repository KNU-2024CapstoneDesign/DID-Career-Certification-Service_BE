package did_career_certification.verifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "VerifierVC")
@Table(name = "VERIFIER_VC_TB")
@NoArgsConstructor
@Getter
public class VC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VP vp;

    @Column(nullable = false, length = 1024)
    private String vcToken;

    public VC(VP vp, String vcToken) {
        this.vp = vp;
        this.vcToken = vcToken;
    }
}
