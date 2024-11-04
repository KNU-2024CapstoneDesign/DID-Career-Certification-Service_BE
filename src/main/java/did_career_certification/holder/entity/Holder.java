package did_career_certification.holder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Holder {

    @Id
    @Column(name = "wallet_Address")
    private String walletAddress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String vpToken;

    public Holder (String walletAddress, String password, String name) {
        this.walletAddress = walletAddress;
        this.password = password;
        this.name = name;
    }
}
