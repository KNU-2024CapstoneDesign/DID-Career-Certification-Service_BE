package did_career_certification.verifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class VP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String holderDid;

    @Column(nullable = false)
    private String verificationMethod;

    public VP(String holderDid, String verificationMethod) {
        this.holderDid = holderDid;
        this.verificationMethod = verificationMethod;
    }
}