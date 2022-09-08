package com.example.envers;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * Wird für @CreatedDate usw. verwendet.
 */
@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SecurityAuditor();
    }

    public class SecurityAuditor implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {

            return Optional.of("TestUser");
        }
    }
}
