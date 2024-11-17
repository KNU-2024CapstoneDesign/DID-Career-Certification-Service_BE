package did_career_certification.global.dto;

import did_career_certification.verifier.entity.Applicant;
import did_career_certification.verifier.entity.VerifierVC;
import java.util.ArrayList;
import java.util.List;

public record VP(String holderName, String holderDid, List<VC> vcList) {

    public Applicant toApplicantEntity() {
        return new Applicant(holderName, holderDid);
    }

    public List<VerifierVC> toVerifierVCEntityList(Applicant applicant) {
        List<VerifierVC> response = new ArrayList<>();
        for(VC vc: vcList)
            response.add(new VerifierVC(applicant, vc));
        return response;
    }

}