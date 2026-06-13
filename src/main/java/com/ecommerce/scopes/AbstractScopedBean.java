package com.ecommerce.scopes;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base class for the bean-scope demo.
 *
 * Every concrete bean gets a unique {@code instanceId} and a {@code createdAt}
 * timestamp the moment Spring constructs it. By comparing these values across
 * HTTP requests you can literally watch when Spring creates a NEW instance and
 * when it reuses an existing one — which is exactly what "scope" controls.
 */
public abstract class AbstractScopedBean {

    /** Assigned once, when this object is constructed. Short form so it's easy to eyeball. */
    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    /** When this particular instance came to life. */
    private final LocalDateTime createdAt = LocalDateTime.now();

    public String getInstanceId() {
        return instanceId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /** Human-readable name of the scope this bean is registered with. */
    public abstract String getScope();
}
