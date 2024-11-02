package did_career_certification.dto;

import did_career_certification.entity.Holder;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String walletAddress, @NotBlank String password,
                              @NotBlank String name, @NotBlank String vpToken) {

    public Holder toEntity(String encodedPassword) {
        return new Holder(walletAddress, encodedPassword, name, vpToken);
    }
}