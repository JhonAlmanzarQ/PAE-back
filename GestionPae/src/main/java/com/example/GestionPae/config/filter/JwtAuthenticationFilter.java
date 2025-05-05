package com.example.GestionPae.config.filter;

import com.example.GestionPae.config.CustomUserDetails;
import com.example.GestionPae.dto.LoginRequestDTO;
import io.jsonwebtoken.Jwts;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/auth/login"); // URL para login
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDTO loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException("Error al autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();



        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TokenJwtConfig.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, TokenJwtConfig.SECRET_KEY.getBytes())
                .compact();

        response.addHeader(TokenJwtConfig.HEADER_AUTHORIZATION, TokenJwtConfig.PREFIX_TOKEN + token);

        // Crear una respuesta JSON personalizada
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Usuario autenticado correctamente");
        responseBody.put("username", user.getUsername());
        responseBody.put("token", TokenJwtConfig.PREFIX_TOKEN + token);
        responseBody.put("rol", user.getAuthorities().toString());
        responseBody.put("id", String.valueOf(user.getId()));

        // Escribir la respuesta
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), responseBody);
    }

}
