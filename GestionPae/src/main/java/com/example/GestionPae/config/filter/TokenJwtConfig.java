package com.example.GestionPae.config.filter;

public class TokenJwtConfig {
    public static final String SECRET_KEY = "8fJVdDkRLP0+Kb9XY2g6nlo3S4fY0LbAqAo9Fzq5Ob5tyDtn2FDysqZKojfxGvWgdc5OAXuP6E4vZLx3LxyI7A==";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final long EXPIRATION_TIME = 7_200_000; // 2 horas
}

