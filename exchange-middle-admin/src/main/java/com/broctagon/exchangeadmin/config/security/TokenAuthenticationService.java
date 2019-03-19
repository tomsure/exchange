package com.broctagon.exchangeadmin.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 10000000;

    private static final String SECRET = "SecretKey";

    private static final String HEADER = "Auth";

    public static void addAuthentication(HttpServletResponse response, String userName) {
       /* String jwt = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
        response.setHeader(HEADER, jwt);*/
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        /*if (!StringUtils.isEmpty(token)) {
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }*/
        return null;
    }
}
