package did_career_certification.service;

import did_career_certification.dto.HolderRegisterRequest;
import did_career_certification.entity.Holder;
import did_career_certification.exception.DuplicateException;
import did_career_certification.repository.HolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HolderService {

    private final HolderRepository holderRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(HolderRegisterRequest request) {
        validateAlreadyExistHolder(request.walletAddress());
        String encodedPassword = passwordEncoder.encode(request.password());
        Holder holder = request.toEntity(encodedPassword);
        holderRepository.save(holder);
    }

    private void validateAlreadyExistHolder(String walletAddress) {
        if(holderRepository.existsById(walletAddress)) {
            throw new DuplicateException("already.exist.walletaddress");
        }
    }
}
