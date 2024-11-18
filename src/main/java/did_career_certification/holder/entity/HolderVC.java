package did_career_certification.holder.entity;

import did_career_certification.holder.dto.MyVCResponse;
import did_career_certification.holder.dto.UpdateVCResponse;
import did_career_certification.jwt.CertificateJwt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HolderVC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Holder holder;

    @Column(nullable = false)
    private String issuerName;

    @Column(nullable = false)
    private String issuerAccount;

    @Column(nullable = false)
    private BigInteger documentId;

    @Column(nullable = false, length = 1024)
    private String certificateToken;

    @Column(nullable = false)
    private LocalDateTime issuanceDate;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public HolderVC(Holder holder, String issuerName, String issuerAccount, BigInteger documentId, String certificateToken, LocalDateTime issuanceDate, LocalDateTime expirationDate) {
        this.holder = holder;
        this.issuerName = issuerName;
        this.issuerAccount = issuerAccount;
        this.documentId = documentId;
        this.certificateToken = certificateToken;
        this.issuanceDate = issuanceDate;
        this.expirationDate = expirationDate;
    }

    public MyVCResponse toDto(Map<String, String> certificate) {
        return new MyVCResponse(
            id,
            issuerName,
            issuanceDate,
            expirationDate,
            certificate
        );
    }

    public UpdateVCResponse toUpdateDto(CertificateJwt jwt) {
        Map<String, String> parseToken = jwt.parseUnivTokenToMap(certificateToken);
        return new UpdateVCResponse(
            id,
            issuerName,
            parseToken.get("course"),
            parseToken.get("academicStatus")
        );
    }
}
