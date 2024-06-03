package com.elaparato.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class OAuth2ResourceServerSecurityConfig {
    private String resourceId;
    private String principalAttribute;

    public String getPrincipalAttribute() {
        return principalAttribute;
    }

    public String getResourceId() {
        return resourceId;
    }
}
