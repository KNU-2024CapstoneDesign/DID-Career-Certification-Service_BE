package did_career_certification.verifier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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

    // Subject
    // 발급 기관 DID
    private String issuerDid;

    // 발급 기관 지갑에 저장된 문서의 id
    private String documentId;

    // 증명서 종류(졸업, 재학 등..)
    private String type;

    // 발급 일자
    private LocalDate issuanceDate;

    // 만료 일자
    private LocalDate expirationDate;

    //Proof
    // 서명 알고리즘
    private String proofType;

    // 서명 시간
    private LocalDate created;

    // 서명 목적(authentication은 Holder가 자신을 증명)
    private String proofPurpose;

    // 공개키 주소
    private String verificationMethod;

    public VC(VP vp, String id, String type, LocalDate issuanceDate, LocalDate expirationDate, String proofType, LocalDate created, String proofpurpose, String verificationMethod) {
        this.vp = vp;
        this.issuerDid = id;
        this.type = type;
        this.issuanceDate = issuanceDate;
        this.expirationDate = expirationDate;
        this.proofType = proofType;
        this.created = created;
        this.proofPurpose = proofpurpose;
        this.verificationMethod = verificationMethod;
    }
}
