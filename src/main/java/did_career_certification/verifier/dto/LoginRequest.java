package did_career_certification.verifier.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String walletAddress, @NotBlank String password) {

}
