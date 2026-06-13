package com.ecommerce.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * SESSION — one instance per HTTP session (i.e. per browser, tracked by the
 * JSESSIONID cookie). Lives until the session expires or is invalidated.
 *
 * Like request scope, it needs a scoped proxy to be safely injected into the
 * singleton controller.
 *
 * Expect: instanceId stays the same across repeated requests from the SAME
 * browser, but differs in a different browser / incognito window / after the
 * session is cleared.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean extends AbstractScopedBean {

    @Override
    public String getScope() {
        return "session";
    }
}
