package did_career_certification.verifier.dto;

import did_career_certification.verifier.entity.Verifier;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String walletAddress, @NotBlank String password,
                              @NotBlank String name) {

    public Verifier toEntity(String encodedPassword) {
        return new Verifier(walletAddress, encodedPassword, name);
    }
}