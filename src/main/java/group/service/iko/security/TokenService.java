package group.service.iko.security;

import group.service.iko.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class TokenService {

    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
    private final String secretKey = "chunempropertyaxpers";

    public String encode(User user) {
        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        Key secretKey = new SecretKeySpec(keyBytes, algorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(user
        .getLogin()).signWith(algorithm, secretKey);

        //

        return builder.compact();
    }

    public Claims decode(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
