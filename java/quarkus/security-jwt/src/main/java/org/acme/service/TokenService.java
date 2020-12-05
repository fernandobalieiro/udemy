package org.acme.service;

import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import java.util.Arrays;

import static org.acme.jwt.utils.TokenUtils.ROLE_USER;
import static org.acme.jwt.utils.TokenUtils.generateTokenString;
import static org.eclipse.microprofile.jwt.Claims.groups;
import static org.eclipse.microprofile.jwt.Claims.preferred_username;
import static org.eclipse.microprofile.jwt.Claims.upn;

@RequestScoped
public class TokenService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    public String generateToken(final String username, final String email, final String birthdate) {
        try {
            final var jwtClaims = new JwtClaims();
            jwtClaims.setIssuer("Acme Org");
            jwtClaims.setJwtId("a-123");
            jwtClaims.setSubject(email);
            jwtClaims.setClaim(upn.name(), email);
            jwtClaims.setClaim(preferred_username.name(), username);
            jwtClaims.setClaim(Claims.birthdate.name(), birthdate);
            jwtClaims.setClaim(groups.name(), Arrays.asList(ROLE_USER));
            jwtClaims.setAudience("using-jwt");
            jwtClaims.setExpirationTimeMinutesInTheFuture(5);
            final var token = generateTokenString(jwtClaims);

            LOGGER.info("Token generated: {}", token);

            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
