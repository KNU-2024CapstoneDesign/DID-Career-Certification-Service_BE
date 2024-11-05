package did_career_certification.util;

import did_career_certification.exception.InvalidTokenException;
import did_career_certification.issuer.dto.VC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
            .parseClaimsJws(token)
            .getBody();
    }

    private boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String generateVCToken(VC vc) {
        // VC의 클레임 데이터 생성
        Map<String, Object> claims = new HashMap<>();
        claims.put("@context", new String[]{"https://www.w3.org/2018/credentials/v1"});
        claims.put("issuer", vc.issuerDid());
        claims.put("issued", new Date());
        claims.put("credentialSubject", Map.of(
            "id", vc.holderDid(),
            "name", vc.subject().name(),
            "college", vc.subject().college().name(),
            "major", vc.subject().major().name(),
            "degree", vc.subject().degree().name(),
            "academicStatus", vc.subject().academicStatus()
        ));

        // JWT 생성 및 서명
        return Jwts.builder()
            .setClaims(claims)                       // 클레임 데이터 설정
            .setIssuer(vc.issuerDid())      // Issuer DID 설정
            .setIssuedAt(new Date())                 // 발급 일자
            .signWith(SignatureAlgorithm.HS256, secretKey) // HMAC 서명
            .compact();                              // JWT 생성
    }

    public Map<String, Object> decodeVCToken(String vcToken) {
        try {
            // JWT 토큰을 파싱하고 서명 검증
            Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(vcToken);
            Claims claims = claimsJws.getBody();

            // 클레임 데이터 추출
            Map<String, Object> vcSubject = new HashMap<>();
            vcSubject.put("issuerDid", claims.get("issuer", String.class));
            vcSubject.put("issued", claims.get("issued", Date.class));
            vcSubject.put("credentialSubject", claims.get("credentialSubject", Map.class));

            // Return the result map
            return vcSubject;

        } catch (SignatureException e) {
            // 서명 검증에 실패한 경우 예외 처리
            throw new IllegalArgumentException("Invalid JWT signature");
        } catch (Exception e) {
            // 기타 예외 처리
            throw new IllegalArgumentException("Error decoding JWT token", e);
        }
    }
}