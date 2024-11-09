package did_career_certification.verifier.dto;

import did_career_certification.verifier.entity.VC;
import did_career_certification.verifier.entity.VP;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record VPRequest(String[] context, String[] type, String holder,
                        List<String> verifiableCredential, Map<String, String> proof) {

    public VP toVPEntity() {
        return new VP(holder, proof.get("verificationMethod"));
    }

    public List<VC> toVCEntity(VP vp) {
        List<VC> vcList = new ArrayList<>();
        for(String vcToken : verifiableCredential)
            vcList.add(new VC(vp, vcToken));
        return vcList;
    }
}