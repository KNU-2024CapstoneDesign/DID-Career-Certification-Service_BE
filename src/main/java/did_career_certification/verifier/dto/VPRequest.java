package did_career_certification.verifier.dto;

import did_career_certification.verifier.entity.VC;
import did_career_certification.verifier.entity.VP;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record VPRequest(String[] context, String[] type, String holder,
                        List<Map<String, String>> verifiableCredential, Map<String, Object> proof) {

    public VP toEntity() {
        return new VP(holder, (String) proof.get("verificationMethod"));
    }

    public List<VC> extractVCList(VP vp) {
        List<VC> vcList = new ArrayList<>();
        for(Map<String, String> vc: verifiableCredential) {
            vcList.add(new VC(
                vp,
                vc.get("id"),
                vc.get("type"),
                LocalDate.parse(vc.get("issuanceDate")),
                LocalDate.parse(vc.get("expirationDate")),
                vc.get("proofType"),
                LocalDate.parse(vc.get("created")),
                vc.get("proofpurpose"),
                vc.get("verificationMethod")
            ));
        }
        return vcList;
    }
}