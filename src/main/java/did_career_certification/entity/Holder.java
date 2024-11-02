package did_career_certification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Holder {

    @Id
    @Column(name = "wallet_Address")
    private String walletAddress;

    private String password;

    private String name;

    private String vpToken;
}
