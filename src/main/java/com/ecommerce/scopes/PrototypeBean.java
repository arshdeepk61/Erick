package com.ecommerce.scopes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * PROTOTYPE — a brand new instance EVERY time the bean is requested from the
 * container.
 *
 * Gotcha: if you inject a prototype bean straight into a singleton via a normal
 * field/constructor, the singleton captures ONE instance at startup and reuses
 * it forever — defeating the point. To actually get a fresh instance per use,
 * ask the container each time. In the controller we do that with
 * {@code ObjectProvider<PrototypeBean>}.
 *
 * Expect: a new instanceId on every lookup — even twice within the same request.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean extends AbstractScopedBean {

    @Override
    public String getScope() {
        return "prototype";
    }
}
