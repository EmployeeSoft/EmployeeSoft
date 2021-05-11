package com.example.demo.security.util;

import com.example.demo.constant.JwtConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static String getSubjectFromJwt(String token) {
        try {
            String subject = Jwts.parser().setSigningKey(JwtConstant.JWT_SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return subject;
        } catch (SignatureException e) {
            LOGGER.warn("invalid Jwt Signature");
        } catch (ExpiredJwtException e) {
            LOGGER.warn("Expired Jwt");
        } catch (Exception e ) {
            LOGGER.warn("Exception Parsing Jwt");
        }
        return null;
    }
}
