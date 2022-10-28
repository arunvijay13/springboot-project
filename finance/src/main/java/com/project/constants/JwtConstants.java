package com.project.constants;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConstants {

      public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

      public static final String SECRET_KEY = "JWT finance application key";

      public static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000;
}


