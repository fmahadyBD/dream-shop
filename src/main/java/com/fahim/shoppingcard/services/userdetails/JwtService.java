package com.fahim.shoppingcard.services.userdetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    /**
     * We found the secret key by the test generate
     * */
    private static final
    String SECRET = "638CBE3A90E0303BF3808F40F95A7F02A24B4B5D029C954CF553F79E9EF1DC0384BE681C249F1223F6B55AA21DC070914834CA22C8DD98E14A872CA010091ACC";
    private static long VALIDITY= TimeUnit.MINUTES.toMinutes(20000);

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .signWith(generateKey())
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .compact();
    }

    public SecretKey generateKey(){
        byte[] decoded = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decoded);
    }


    //2nd part

    public String extractUsername(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(generateKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token){
        Claims claims =getClaims(token);
        return claims.getExpiration()
                .after(Date.from(Instant.now()));
    }

}
