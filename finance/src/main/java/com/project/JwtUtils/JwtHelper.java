package com.project.JwtUtils;

import com.project.constants.JwtConstants;
import com.project.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtHelper {

      public String JWTGenerator(String username) {
            long time = System.currentTimeMillis();
            long expiredPeriod = time + JwtConstants.TOKEN_VALIDITY;

            Map<String, Object> userClaims = new LinkedHashMap<>();
            userClaims.put("user", username);
            userClaims.put("expiry", expiredPeriod);

            return Jwts.builder().signWith(JwtConstants.ALGORITHM, JwtConstants.SECRET_KEY)
                    .setIssuedAt(new Date()).setExpiration(new Date(expiredPeriod))
                    .setClaims(userClaims).setHeader(getHeader()).compact();
      }

      public Claims validateToken(String token) throws Exception {
            return Jwts.parser()
                    .setSigningKey("secretkey")
                    .parseClaimsJws(token).getBody();
      }

      private Map<String, Object> getHeader() {
            Map<String, Object> headers =  new LinkedHashMap<>();
            headers.put("alg", "HS256");
            headers.put("type", "JWT");

            return headers;
      }

}