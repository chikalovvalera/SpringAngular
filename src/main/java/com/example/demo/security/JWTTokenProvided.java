package com.example.demo.security;

import com.example.demo.entity.Users;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvided {

    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvided.class);

    public String generationToken(Authentication authentication){
        Users user = (Users)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(now.getTime()+SecurityConstants.EXPIRATION_TIME);

        String userId = Long.toString(user.getId());
        Map<String, Object> claimsMap  = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("username", user.getEmail());
        claimsMap.put("firstname", user.getNick());
        claimsMap.put("lastname", user.getLastname());

        return Jwts.builder().addClaims(claimsMap).setIssuedAt(now).setExpiration(exp).signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }

    public boolean validation(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e){
            LOG.error(e.getMessage());
            return false;
        }
    }

    public Long getUserIdByToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");
        return Long.parseLong(id);
    }
}
