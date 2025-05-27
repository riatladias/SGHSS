package br.com.riatladias.sghss.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            var getSubject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return getSubject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }

    }
}
