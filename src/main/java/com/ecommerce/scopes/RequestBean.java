package com.ecommerce.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * REQUEST — one instance per HTTP request. When the request completes, the
 * instance is discarded.
 *
 * {@code proxyMode = TARGET_CLASS} is required here: the controller is a
 * singleton, but a request-scoped bean lives much shorter. The proxy is a
 * lightweight stand-in injected once into the singleton; on each method call it
 * transparently routes to the real instance bound to the CURRENT request.
 *
 * Expect: a new instanceId on every request, regardless of user/session.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBean extends AbstractScopedBean {

    @Override
    public String getScope() {
        return "request";
    }
}
