package com.ecommerce.scopes;

import org.springframework.stereotype.Component;

/**
 * SINGLETON — the default scope.
 *
 * Spring creates exactly ONE instance per application context (eagerly, at
 * startup) and hands that same object to everyone who asks for it.
 *
 * Expect: instanceId is identical on every request, for the whole app lifetime.
 */
@Component
public class SingletonBean extends AbstractScopedBean {

    @Override
    public String getScope() {
        return "singleton (default)";
    }
}
