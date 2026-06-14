package com.ecommerce.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // You can check database connection, external API, disk space, etc.
        boolean isServiceUp = true; 

        if (isServiceUp) {
            return Health.up()
                    .withDetail("Ecommerce Service", "Is running smoothly")
                    .withDetail("External API", "Connected")
                    .build();
        } else {
            return Health.down()
                    .withDetail("Error", "External API is unreachable")
                    .build();
        }
    }
}
