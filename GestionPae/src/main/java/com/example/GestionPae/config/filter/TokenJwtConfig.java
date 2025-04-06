package com.example.GestionPae.config.filter;

public class TokenJwtConfig {
    public static final String SECRET_KEY = "ClaveSecretaSeguraDeAlMenos32Caracteres123";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final long EXPIRATION_TIME = 7_200_000; // 2 horas
}
