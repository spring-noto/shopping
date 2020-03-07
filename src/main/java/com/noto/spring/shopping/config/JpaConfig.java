package com.noto.spring.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") //JPA Auditing 활성화
public class JpaConfig {

    @Bean("auditorProvider")
    public AuditorAware<String> auditorPprovider() {
        return () -> Optional.of("이영우");
    }
}