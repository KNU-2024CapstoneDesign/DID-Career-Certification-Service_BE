package did_career_certification.service;

import did_career_certification.dto.LoginRequest;
import did_career_certification.dto.RegisterRequest;
import did_career_certification.dto.TokenResponse;
import did_career_certification.entity.Holder;
import did_career_certification.exception.DuplicateException;
import did_career_certification.exception.NotFoundException;
import did_career_certification.exception.PermissionException;
import did_career_certification.repository.HolderRepository;
import did_career_certification.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HolderService {

    private final HolderRepository holderRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {
        validateAlreadyExistHolder(request.walletAddress());
        String encodedPassword = passwordEncoder.encode(request.password());
        Holder holder = request.toEntity(encodedPassword);
        holderRepository.save(holder);
    }

    public TokenResponse login(LoginRequest request) {
        Holder holder = holderRepository.findById(request.walletAddress())
            .orElseThrow(() -> new NotFoundException("not.found.user"));
        if(!passwordEncoder.matches(request.password(), holder.getPassword())) {
            throw new PermissionException("not.match.password");
        }
        return new TokenResponse(jwtUtil.generateToken(request.walletAddress()));
    }

    private void validateAlreadyExistHolder(String walletAddress) {
        if(holderRepository.existsById(walletAddress)) {
            throw new DuplicateException("already.exist.walletaddress");
        }
    }
}
