package did_career_certification.holder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "HolderVC")
@NoArgsConstructor
@Getter
@Table(name = "HOLDER_VC_TB")
public class VC {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Holder holder;

    @Column(nullable = false, length = 1024)
    private String vcToken;

    public VC(Holder holder, String vcToken) {
        this.holder = holder;
        this.vcToken = vcToken;
    }
}