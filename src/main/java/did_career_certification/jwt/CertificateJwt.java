package did_career_certification.jwt;

import did_career_certification.global.dto.KangwonUnivCertificate;
import did_career_certification.global.dto.VC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CertificateJwt {

    private final SecretKey secretKey;
    private final long expirationTime;

    private static final String STUDENT_ID = "studentId";
    private static final String COURSE = "course";
    private static final String ACADEMIC_STATUS = "academicStatus";

    public CertificateJwt(@Value("${jwt.certificate-expiration-time}")long expirationTime) {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expirationTime = expirationTime;
    }

    public String encryptCertificate(KangwonUnivCertificate certificate) {
        return Jwts.builder()
            .claims(certificate.toMap())
            .signWith(SignatureAlgorithm.HS256, secretKey) // HMAC 서명
            .compact();
    }

    public Map<String, String> parseUnivTokenToMap(String certificateToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(certificateToken);
            Claims claims = claimsJws.getPayload();

            Map<String, String> certificate = new HashMap<>();
            certificate.put(STUDENT_ID, claims.get(STUDENT_ID).toString());
            certificate.put(COURSE, claims.get(COURSE).toString());
            certificate.put(ACADEMIC_STATUS, claims.get(ACADEMIC_STATUS).toString());

            return certificate;
        } catch (SignatureException e) {
            // 서명 검증에 실패한 경우 예외 처리
            throw new IllegalArgumentException("Invalid JWT signature");
        } catch (Exception e) {
            // 기타 예외 처리
            throw new IllegalArgumentException("Error decoding JWT token", e);
        }
    }
}
