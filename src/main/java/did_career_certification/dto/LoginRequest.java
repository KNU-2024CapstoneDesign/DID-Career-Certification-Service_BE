package did_career_certification.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String walletAddress, @NotBlank String password) {

}
