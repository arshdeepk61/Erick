package com.ecommerce.scopes;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Demonstrates all the standard Spring bean scopes in one place.
 *
 * This controller is itself a SINGLETON (the default for @RestController), which
 * is what makes the demo interesting: it shows how shorter-lived beans
 * (request / session / prototype) still behave correctly even though they live
 * inside a long-lived singleton — thanks to scoped proxies and ObjectProvider.
 *
 * Try it:
 *   GET http://localhost/scopes        <- call it several times
 *   GET http://localhost/scopes         in a different browser / incognito
 *
 * What to watch in the JSON response:
 *   singleton    -> instanceId NEVER changes
 *   application  -> instanceId NEVER changes (one per ServletContext)
 *   session      -> stable within one browser, changes across browsers/sessions
 *   request      -> a NEW instanceId on every single request
 *   prototype_*  -> the two prototype calls differ from each other AND every request
 */
@RestController
@RequestMapping("/scopes")
public class BeanScopeController {

    private final SingletonBean singletonBean;
    private final ApplicationBean applicationBean;
    private final SessionBean sessionBean;   // injected as a session-scoped proxy
    private final RequestBean requestBean;    // injected as a request-scoped proxy

    /**
     * For prototype we deliberately do NOT inject the bean directly. A directly
     * injected prototype would be resolved once at startup and frozen into this
     * singleton. ObjectProvider lets us ask the container for a fresh instance
     * on demand, which is how you correctly use prototype beans inside singletons.
     */
    private final ObjectProvider<PrototypeBean> prototypeProvider;

    public BeanScopeController(SingletonBean singletonBean,
                               ApplicationBean applicationBean,
                               SessionBean sessionBean,
                               RequestBean requestBean,
                               ObjectProvider<PrototypeBean> prototypeProvider) {
        this.singletonBean = singletonBean;
        this.applicationBean = applicationBean;
        this.sessionBean = sessionBean;
        this.requestBean = requestBean;
        this.prototypeProvider = prototypeProvider;
    }

    @GetMapping
    public Map<String, Object> showScopes() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("singleton", describe(singletonBean));
        result.put("application", describe(applicationBean));
        result.put("session", describe(sessionBean));
        result.put("request", describe(requestBean));

        // Two lookups within the SAME request -> two DIFFERENT prototype instances.
        result.put("prototype_call_1", describe(prototypeProvider.getObject()));
        result.put("prototype_call_2", describe(prototypeProvider.getObject()));

        return result;
    }

    private Map<String, Object> describe(AbstractScopedBean bean) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("scope", bean.getScope());
        m.put("instanceId", bean.getInstanceId());
        m.put("createdAt", bean.getCreatedAt().toString());
        m.put("description", getScopeDescription(bean.getScope()));
        return m;
    }

    private String getScopeDescription(String scope) {
        return switch (scope.toLowerCase()) {
            case "singleton (default)" -> "ONE instance per Spring Context. Reused everywhere.";
            case "prototype" -> "A NEW instance every time it is requested from the container.";
            case "request" -> "One instance per HTTP request. Discarded when request ends.";
            case "session" -> "One instance per HTTP session. Shared across requests from the same browser.";
            case "application" -> "One instance per ServletContext. Shared across all Spring contexts in the same web app.";
            default -> "Unknown scope.";
        };
    }
}
