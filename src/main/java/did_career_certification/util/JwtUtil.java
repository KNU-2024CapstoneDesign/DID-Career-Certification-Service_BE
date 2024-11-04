package did_career_certification.util;

import did_career_certification.exception.InvalidTokenException;
import did_career_certification.issuer.dto.VC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
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
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
            .setSubject(vc.holderDid())
            .setIssuer(vc.issuerDid())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .claim("claims", vc.subject())
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }
}
// @context 설정