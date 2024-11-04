package did_career_certification.holder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Vc {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Holder holder;

    private Long IssuerId;

    private String vcToken;
}
