package did_career_certification.verifier.entity;

import did_career_certification.holder.dto.MyVCResponse;
import did_career_certification.util.JwtUtil;
import did_career_certification.verifier.dto.VPResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class VP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderName;

    //@Column(nullable = false)
    private String holderDid;

    //@Column(nullable = false)
    private String documentId;

    //Proof
    // 서명 알고리즘
    private String proofType;

    // 서명 시간
    private LocalDate created;

    // 서명 목적(authentication은 Holder가 자신을 증명)
    private String proofPurpose;

    //@Column(nullable = false)
    private String verificationMethod;

    public VP(String holderDid, String verificationMethod) {
        this.holderDid = holderDid;
        this.verificationMethod = verificationMethod;
    }

    public VPResponse toDto(List<VC> vcTokenList, JwtUtil jwtUtil) {
        List<MyVCResponse> vcList = new ArrayList<>();
        int i = 0;
        for(VC token: vcTokenList) {
            Map<String, String> vc = jwtUtil.decodeVCToken(token.getVcToken());
            vcList.add(new MyVCResponse(
                (long) ++i,
                vc.get("issuerName"),
                vc.get("issueDate"),
                jwtUtil.decodeCertificateToken(vc.get("certificateKeySet"), vc.get("certificateToken"))
            ));

        }
        return new VPResponse(id, holderName, vcList);
    }
}