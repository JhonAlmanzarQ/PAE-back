package com.example.GestionPae.config.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JwtValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.replace("Bearer ", "");
            Key secretKey = Keys.hmacShaKeyFor(TokenJwtConfig.SECRET_KEY.getBytes());

            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject(); // El "sub" del token

            // 👇 Aquí adaptamos la extracción del campo "roles" como lista de mapas
            List<Map<String, String>> roles = claims.get("roles", List.class);

            if (username != null) {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                if (roles != null) {
                    for (Map<String, String> role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                    }
                }

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (Exception e) {
            // Si el token es inválido, simplemente no autenticamos
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}