package did_career_certification.holder.dto;

import did_career_certification.holder.entity.Holder;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String walletAddress, @NotBlank String password,
                              @NotBlank String name) {

    public Holder toEntity(String encodedPassword) {
        return new Holder(walletAddress, encodedPassword, name);
    }
}