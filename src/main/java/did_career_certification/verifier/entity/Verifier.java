package did_career_certification.verifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "VerifierVerifier")
@NoArgsConstructor
@Getter
@Table(name = "VERIFIER_VERIFIER_TB")
public class Verifier {

    @Id
    @Column(name = "wallet_Address")
    private String walletAddress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    public Verifier(String walletAddress, String password, String name) {
        this.walletAddress = walletAddress;
        this.password = password;
        this.name = name;
    }
}
