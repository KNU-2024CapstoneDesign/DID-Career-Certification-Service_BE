package did_career_certification.verifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class VP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderName;

    @Column(nullable = false)
    private String holderDid;

    @Column(nullable = false)
    private String documentId;

    //Proof
    // 서명 알고리즘
    private String proofType;

    // 서명 시간
    private LocalDate created;

    // 서명 목적(authentication은 Holder가 자신을 증명)
    private String proofPurpose;

    @Column(nullable = false)
    private String verificationMethod;

    public VP(String holderDid, String verificationMethod) {
        this.holderDid = holderDid;
        this.verificationMethod = verificationMethod;
    }
}