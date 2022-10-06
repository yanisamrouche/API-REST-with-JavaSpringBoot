package myboot.app4.test;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class JWTTest {

    @Test
    @Order(1)
    public void createJWT() {
        // Maintenant et cinq secondes plus tard
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.SECOND, 5);
        Date nowPlus5Seconds = c.getTime();

        // un secret pour signer le token
        String secretText = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        byte[] secret = TextCodec.BASE64.decode(secretText);

        // construction d'un JWT
        String jwt = Jwts.builder()//
                .setIssuer("Jean-Luc MASSAT")//
                .setSubject("Test JWT")//
                .claim("name", "JLM")//
                .claim("scope", "admin")//
                .setIssuedAt(now)//
                .setExpiration(nowPlus5Seconds)//
                .signWith(SignatureAlgorithm.HS256, secret).compact();

        log.info("Generated JWT is: "+ jwt);
    }

    @Test
    @Order(2)
    @ExceptionHandler(ExpiredJwtException.class)
    public void decodeJWT() {
        // Décodage d'un JWT
        final String jws = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKZWFuLUx1YyBNQVNTQVQiLCJzdWIiOiJUZXN0IEpXVCIsIm5hbWUiOiJKTE0iLCJzY29wZSI6ImFkbWluIiwiaWF0IjoxNjY0Nzc4ODQ2LCJleHAiOjE2NjQ3Nzg4NTF9.YoPYXUgKWyJGp3Q-GQmmi0pruTuonIMnShkwHnN8CiE";
        final String secret = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

        assertThrows(ExpiredJwtException.class, () -> {
            Jws<Claims> jwsDecoded = Jwts.parser()//
                    .setSigningKey(secret)//
                    .parseClaimsJws(jws);
        });

    }

}
