package did_career_certification.util;

import did_career_certification.exception.InvalidTokenException;
import did_career_certification.holder.dto.MyVCResponse;
import did_career_certification.issuer.dto.VC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expirationTime;

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int BEARER_PREFIX_LENGTH = 7;
    private static final String ID = "id";

    public JwtUtil(@Value("${jwt.expiration-time}")long expirationTime) {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expirationTime = expirationTime;
    }

    public String generateToken(String walletAddress) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
            .claim(ID, walletAddress)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public String parseToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            if (isTokenExpired(claims.getExpiration())) {
                throw new InvalidTokenException("token.expired");
            }
            String walletAddress = claims.get(ID).toString();
            return walletAddress;
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("token.expired");
        } catch (SignatureException e) {
            throw new InvalidTokenException("token.signature.invalid");
        } catch (Exception e) {
            throw new InvalidTokenException("invalid.token");
        }
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String encryptCertificate(Map<String, String> subject) {
        return Jwts.builder()
            .claims(subject)               // 발급 일자
            .signWith(SignatureAlgorithm.HS256, secretKey) // HMAC 서명
            .compact();
    }

    public String generateVCToken(VC vc) {
        // VC의 클레임 데이터 생성
        Map<String, Object> claims = new HashMap<>();
        claims.put("issuerName", "강원대학교(춘천)");
        claims.put("issuanceDate", new Date().toString());
        claims.put("certificateToken", vc.certificateToken());
        claims.put("certificateKeySet", "studentId,name,course,academicStatus");
        claims.put("issuerDid", vc.issuerDid());
        claims.put("txHash", vc.txHash());

        // JWT 생성 및 서명
        return Jwts.builder()
            .claims(claims)
            .issuer(vc.issuerDid())
            .issuedAt(new Date())                 // 발급 일자
            .signWith(SignatureAlgorithm.HS256, secretKey) // HMAC 서명
            .compact();                              // JWT 생성
    }

    public Map<String, String> decodeVCToken(String vcToken) {
        try {
            // JWT 토큰을 파싱하고 서명 검증
            Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(vcToken);
            Claims claims = claimsJws.getPayload();

            Map<String, String> vc = new HashMap<>();
            vc.put("issuerName", (String) claims.get("issuerName"));
            vc.put("issuanceDate", (String) claims.get("issuanceDate"));
            vc.put("certificateToken", (String) claims.get("certificateToken"));
            vc.put("certificateKeySet", (String) claims.get("certificateKeySet"));

            // 클레임 데이터 추출
            return vc;
        } catch (SignatureException e) {
            // 서명 검증에 실패한 경우 예외 처리
            throw new IllegalArgumentException("Invalid JWT signature");
        } catch (Exception e) {
            // 기타 예외 처리
            throw new IllegalArgumentException("Error decoding JWT token", e);
        }
    }

    public Map<String, String> decodeCertificateToken(String certificateKeySet, String certificateToken) {
        try {
            // JWT 토큰을 파싱하고 서명 검증
            Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(certificateToken);
            Claims claims = claimsJws.getPayload();

            Map<String, String> subject = new HashMap<>();
            for(String key: certificateKeySet.split(",")) {
                subject.put(key, (String) claims.get(key));
            }

            // 클레임 데이터 추출
            return subject;
        } catch (SignatureException e) {
            // 서명 검증에 실패한 경우 예외 처리
            throw new IllegalArgumentException("Invalid JWT signature");
        } catch (Exception e) {
            // 기타 예외 처리
            throw new IllegalArgumentException("Error decoding JWT token", e);
        }
    }
}