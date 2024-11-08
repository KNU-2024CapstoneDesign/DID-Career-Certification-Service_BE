package did_career_certification.verifier.service;

import did_career_certification.exception.DuplicateException;
import did_career_certification.exception.NotFoundException;
import did_career_certification.exception.PermissionException;
import did_career_certification.verifier.dto.LoginRequest;
import did_career_certification.verifier.dto.RegisterRequest;
import did_career_certification.verifier.dto.TokenResponse;
import did_career_certification.verifier.entity.Verifier;
import did_career_certification.verifier.repository.VerifierRepository;
import did_career_certification.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifierService {

    private final VerifierRepository verifierRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {
        validateAlreadyExistHolder(request.walletAddress());
        String encodedPassword = passwordEncoder.encode(request.password());
        Verifier verifier = request.toEntity(encodedPassword);
        verifierRepository.save(verifier);
    }

    public TokenResponse login(LoginRequest request) {
        Verifier verifier = verifierRepository.findById(request.walletAddress())
            .orElseThrow(() -> new NotFoundException("not.found.user"));
        if(!passwordEncoder.matches(request.password(), verifier.getPassword())) {
            throw new PermissionException("not.match.password");
        }
        return new TokenResponse(jwtUtil.generateToken(request.walletAddress()));
    }

    private void validateAlreadyExistHolder(String walletAddress) {
        if(verifierRepository.existsById(walletAddress)) {
            throw new DuplicateException("already.exist.walletaddress");
        }
    }
}
