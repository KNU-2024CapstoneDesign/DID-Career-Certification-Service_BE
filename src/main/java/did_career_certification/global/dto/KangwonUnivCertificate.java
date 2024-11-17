package did_career_certification.global.dto;

import java.util.HashMap;
import java.util.Map;

public record KangwonUnivCertificate(String holderDid, Long studentId, String name, String course, String academicStatus) {

    public Map<String, String> toMap() {
        Map<String, String> certificateMap = new HashMap<>();
        certificateMap.put("holderDid", holderDid);
        certificateMap.put("studentId", String.valueOf(studentId));
        certificateMap.put("name", name);
        certificateMap.put("course", course);
        certificateMap.put("academicStatus", academicStatus);
        return certificateMap;
    }
}
