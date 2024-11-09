package did_career_certification.verifier.dto;

import did_career_certification.holder.dto.MyVCResponse;
import java.util.List;

public record VPResponse(Long id, String name, List<MyVCResponse> vcList) {

}
