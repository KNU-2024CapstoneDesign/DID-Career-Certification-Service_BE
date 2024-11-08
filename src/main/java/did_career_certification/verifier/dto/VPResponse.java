package did_career_certification.verifier.dto;

import did_career_certification.verifier.entity.VC;
import java.util.List;

public record VPResponse(Long id, String name, List<VC> vcList) {

}
