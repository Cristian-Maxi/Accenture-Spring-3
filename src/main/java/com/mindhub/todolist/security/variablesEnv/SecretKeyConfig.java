package com.mindhub.todolist.security.variablesEnv;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwtkey")
@Data
public class SecretKeyConfig {
    private String SECRET_KEY;
}

