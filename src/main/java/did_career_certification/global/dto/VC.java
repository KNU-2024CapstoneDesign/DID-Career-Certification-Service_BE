package did_career_certification.global.dto;

import did_career_certification.holder.entity.Holder;
import did_career_certification.holder.entity.HolderVC;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record VC(String issuerName, String issuerAccount, LocalDateTime issuanceDate,
                 LocalDateTime expirationDate, BigInteger documentId, String certificateToken) {

    public HolderVC toHolderVC(Holder holder) {
        return new HolderVC(
            holder,
            issuerName,
            issuerAccount,
            documentId,
            certificateToken,
            issuanceDate,
            expirationDate
        );
    }

    public VC(HolderVC holderVC) {
        this(holderVC.getIssuerName(), holderVC.getIssuerAccount(), holderVC.getIssuanceDate(),
            holderVC.getExpirationDate(), holderVC.getDocumentId(), holderVC.getCertificateToken());
    }
}

