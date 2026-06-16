package com.ecommerce.scopes;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * BEGINNER-FRIENDLY bean scope demo.
 *
 * This controller returns a simple web PAGE (not JSON) that explains, in plain
 * English, what is happening. A brand-new student can just open it in a browser
 * and read along.
 *
 * Open in your browser:
 *     http://localhost/scope-demo
 *
 * Then keep pressing REFRESH and read the page — it tells you what to look for.
 */
@RestController
@RequestMapping("/scope-demo")
public class ScopeDemoController {

    // This controller is a SINGLETON (Spring's default). Because there is only ONE
    // ScopeDemoController object for the whole app, this counter is shared by every
    // request and keeps growing. That is your first proof of what "singleton" means.
    private final AtomicInteger pageViews = new AtomicInteger(0);

    private final SingletonBean singletonBean;
    private final ApplicationBean applicationBean;
    private final SessionBean sessionBean;     // session-scoped proxy
    private final RequestBean requestBean;       // request-scoped proxy
    private final ObjectProvider<PrototypeBean> prototypeProvider;

    public ScopeDemoController(SingletonBean singletonBean,
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

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String demo() {
        int viewNumber = pageViews.incrementAndGet();

        // Two prototype lookups in the SAME page load -> two DIFFERENT objects.
        String prototypeA = prototypeProvider.getObject().getInstanceId();
        String prototypeB = prototypeProvider.getObject().getInstanceId();

        return PAGE.formatted(
                viewNumber,
                singletonBean.getInstanceId(),
                applicationBean.getInstanceId(),
                sessionBean.getInstanceId(),
                requestBean.getInstanceId(),
                prototypeA,
                prototypeB
        );
    }

    // A plain HTML template. The %s placeholders are filled in by .formatted(...) above.
    // Each row shows a scope, its live ID, and a one-line "what this means".
    private static final String PAGE = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="utf-8">
              <title>Spring Bean Scopes — Beginner Demo</title>
              <style>
                body { font-family: system-ui, sans-serif; max-width: 760px; margin: 30px auto; color: #222; line-height: 1.5; }
                h1 { color: #2e7d32; }
                .card { border: 1px solid #ddd; border-radius: 10px; padding: 16px 20px; margin: 14px 0; }
                .id { font-family: monospace; font-size: 1.2em; background: #f3f3f3; padding: 2px 8px; border-radius: 6px; }
                .same { border-left: 6px solid #2e7d32; }   /* green = stays the same */
                .changes { border-left: 6px solid #c62828; } /* red = changes */
                .views { font-size: 1.1em; background: #fff8e1; padding: 10px 14px; border-radius: 8px; }
                code { background: #eee; padding: 1px 5px; border-radius: 4px; }
                small { color: #666; }
              </style>
            </head>
            <body>
              <h1>What is a Spring "bean scope"?</h1>
              <p>A <b>bean</b> is just an object that Spring builds and manages for you.
                 A bean's <b>scope</b> answers two questions:
                 <i>how many copies exist</i> and <i>how long does each one live?</i></p>
              <p>Each box below shows a live <span class="id">ID</span>. The ID is created the
                 moment Spring makes that object. So: <b>if the ID changes, Spring made a NEW object.
                 If it stays the same, Spring reused the SAME object.</b></p>

              <p class="views">You have loaded this page <b>%s</b> time(s).<br>
                 <small>This number keeps growing because the controller is a <b>singleton</b> — there is only one
                 of it, so its counter is shared across every request.</small></p>

              <div class="card same">
                <b>singleton</b> (the default) &nbsp; ID: <span class="id">%s</span><br>
                <small>ONE object for the whole app. This ID will <b>never</b> change, no matter how often you refresh.</small>
              </div>

              <div class="card same">
                <b>application</b> &nbsp; ID: <span class="id">%s</span><br>
                <small>One object per web application. Also <b>never</b> changes — very similar to singleton.</small>
              </div>

              <div class="card same">
                <b>session</b> &nbsp; ID: <span class="id">%s</span><br>
                <small>One object per browser session. Stays the same when YOU refresh, but is
                <b>different in another browser or an incognito window</b>.</small>
              </div>

              <div class="card changes">
                <b>request</b> &nbsp; ID: <span class="id">%s</span><br>
                <small>A fresh object for <b>every single request</b>. This ID changes <b>every time</b> you refresh.</small>
              </div>

              <div class="card changes">
                <b>prototype</b> &nbsp; ID #1: <span class="id">%s</span> &nbsp; ID #2: <span class="id">%s</span><br>
                <small>A brand-new object <b>every time you ask for one</b>. We asked twice on this single page load,
                so the two IDs above are <b>different from each other</b> — and both change on every refresh.</small>
              </div>

              <h2>👉 Try this yourself</h2>
              <ol>
                <li><b>Refresh this page a few times.</b> Watch: green boxes stay the same, red boxes change.</li>
                <li><b>Open this same URL in an incognito window.</b> Now the <b>session</b> ID changes too
                    (it's a different browser session).</li>
                <li>Notice the two <b>prototype</b> IDs are always different — even within one page load.</li>
              </ol>
              <p><small>Green = same object reused &nbsp;•&nbsp; Red = new object created</small></p>
            </body>
            </html>
            """;
}
