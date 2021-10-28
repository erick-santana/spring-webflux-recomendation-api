package com.project.reactiveprogramming.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Setter
@Getter
public class ApiConfig {

    private String url;
    private Integer connectionTimeout;
    private Integer readTimeout;
}
