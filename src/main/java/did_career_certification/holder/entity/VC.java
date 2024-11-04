package did_career_certification.holder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
public class VC {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @Column(nullable = false)
    private Holder holder;

    @Column(nullable = false)
    private String vcToken;

    public VC(Holder holder, String vcToken) {
        this.holder = holder;
        this.vcToken = vcToken;
    }
}
