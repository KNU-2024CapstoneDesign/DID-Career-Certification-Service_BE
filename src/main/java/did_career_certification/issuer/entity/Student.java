package did_career_certification.issuer.entity;

import did_career_certification.global.dto.KangwonUnivCertificate;
import did_career_certification.issuer.enums.AcademicStatus;
import did_career_certification.issuer.enums.College;
import did_career_certification.issuer.enums.Degree;
import did_career_certification.issuer.enums.Major;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private College college;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Major major;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Degree degree;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AcademicStatus academicStatus;

    public KangwonUnivCertificate createCertificate(String holderDid) {
        return new KangwonUnivCertificate(
            holderDid,
            id,
            name,
            college + " " + major,
            academicStatus.toString()
        );
    }
}
