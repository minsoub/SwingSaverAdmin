package kr.co.swingsaver.security;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import kr.co.swingsaver.model.RoleType;
import kr.co.swingsaver.service.LoginService;
import lombok.val;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.issuer}")
    private String ISSUER;

    @Value("${jwt.access-expired}")
    private long JWT_ACCESS_TOKEN_VALIDITY;

    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
    private Key signingKey;

    public static final int OK                    = 0x00;
    public static final int EXCEPTION_SIGNATURE   = 0x90;
    public static final int EXCEPTION_MALFORMED   = 0x91;
    public static final int EXCEPTION_EXPIRED     = 0x92;
    public static final int EXCEPTION_UNSUPPORTED = 0x93;
    public static final int EXCEPTION_ILLEGAL     = 0x94;

    @Autowired
    LoginService loginService;

    @PostConstruct
    public void init() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        this.signingKey = new SecretKeySpec(apiKeySecretBytes, algorithm.getJcaName());
    }


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(AuthUserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        val isSuperAdmin = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_"+ RoleType.SUPERADMIN));
        if (isSuperAdmin) {
            claims.put("role","superadmin");
        } else {
            val isAdmin = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_"+ RoleType.ADMIN));
            if (isAdmin) {
                claims.put("role","admin");
            } else {
                claims.put("role","user");
            }
        }

        val myName = userDetails.getName();
        claims.put("myName", myName);

        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY * 1000))
                .signWith(signingKey).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        val username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token) && loginService.existsByAccessToken(username);
    }

    public int validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
            return OK;
        } catch (SecurityException e) {
            return EXCEPTION_SIGNATURE;
        } catch (MalformedJwtException e) {
            return EXCEPTION_MALFORMED;
        } catch (ExpiredJwtException e) {
            return EXCEPTION_EXPIRED;
        } catch (UnsupportedJwtException e) {
            return EXCEPTION_UNSUPPORTED;
        } catch (IllegalArgumentException e) {
            return EXCEPTION_ILLEGAL;
        }
    }

}
