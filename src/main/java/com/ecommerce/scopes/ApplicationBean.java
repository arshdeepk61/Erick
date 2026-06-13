package com.ecommerce.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * APPLICATION — one instance per ServletContext (the whole web application).
 *
 * Subtly different from singleton: singleton is "one per Spring container",
 * application is "one per ServletContext". They usually look the same in a
 * simple app, but if multiple Spring contexts share one ServletContext, an
 * application-scoped bean is shared across ALL of them while each context has
 * its own singleton.
 *
 * Expect: same instanceId for the entire app lifetime, like singleton.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationBean extends AbstractScopedBean {

    @Override
    public String getScope() {
        return "application";
    }
}
