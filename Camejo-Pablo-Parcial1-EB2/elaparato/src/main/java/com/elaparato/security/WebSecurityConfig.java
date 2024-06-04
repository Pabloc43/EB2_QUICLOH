package com.elaparato.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String VENDEDOR = "vendedor";
    public static final String REPOSITOR = "repositor";
    private final KeyCloakJwtAuthenticationConverter keyCloakJwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/productos/create").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/productos/getall").hasAnyRole(ADMIN, USER, VENDEDOR, REPOSITOR)
                .requestMatchers(HttpMethod.PUT, "/api/productos/edit").hasAnyRole(ADMIN, REPOSITOR)
                .requestMatchers(HttpMethod.POST, "/api/ventas/create").hasAnyRole(ADMIN, VENDEDOR)
                .requestMatchers(HttpMethod.GET, "/api/ventas/getall").hasAnyRole(ADMIN, VENDEDOR)
                .requestMatchers(HttpMethod.PUT, "/api/ventas/edit").hasAnyRole(ADMIN, VENDEDOR)
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(keyCloakJwtAuthenticationConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/el-aparato-guekdjian-anthony/protocol/openid-connect/certs").build();
    }
}
