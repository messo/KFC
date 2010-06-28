package hu.sch.kfc.server;

import hu.sch.kfc.client.service.EventService;
import hu.sch.kfc.shared.Event;
import hu.sch.kfc.shared.LikeEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.sun.grizzly.comet.CometContext;
import com.sun.grizzly.comet.CometEngine;
import com.sun.grizzly.comet.CometEvent;
import com.sun.grizzly.comet.CometHandler;

/**
 * Egy olyan servlet, amely Glassfish alatt működik remekül kihasználva a
 * Grizzly Comet-et, figyel a beérkező RPC kérésre, és ha van válasz, akkor
 * válaszol.
 * 
 * @author messo
 */
public class EventServlet extends HttpServlet {

    private static final long serialVersionUID = -8986928591792103055L;
    private String contextPath = null;
    private AtomicInteger counter = new AtomicInteger(0);

    private class CounterHandler implements CometHandler<HttpServletResponse> {

        private HttpServletResponse response;

        @Override
        public void attach(HttpServletResponse res) {
            response = res;
        }

        @Override
        public void onEvent(CometEvent event) throws IOException {
            if (CometEvent.NOTIFY == event.getType()) {
                int count = counter.get();
                // writer.write(String.valueOf(count));
                Method method = null;
                Event myEvent = (Event) event.attachment();
                try {
                    method = EventService.class.getMethod("getEvent", null);
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                PrintWriter writer = response.getWriter();
                try {
                    writer.write(RPC.encodeResponseForSuccess(method, myEvent));
                } catch (SerializationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                writer.flush();
                event.getCometContext().resumeCometHandler(this);
            }
        }

        @Override
        public void onInitialize(CometEvent arg0) throws IOException {
            // TODO Auto-generated method stub

        }

        @Override
        public void onInterrupt(CometEvent arg0) throws IOException {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTerminate(CometEvent arg0) throws IOException {
            // TODO Auto-generated method stub

        }

        private void removeThisFromContext() throws IOException {
            response.getWriter().close();
            CometContext context = CometEngine.getEngine().getCometContext(
                    contextPath);
            context.removeCometHandler(this);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        contextPath = context.getContextPath() + "/comet";
        CometEngine engine = CometEngine.getEngine();
        CometContext<?> cometContext = engine.register(contextPath);
        cometContext.setExpirationDelay(30 * 1000);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        counter.incrementAndGet();
        CometEngine engine = CometEngine.getEngine();
        CometContext<?> context = engine.getCometContext(contextPath);
        context.notify(new LikeEvent(counter.get()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        CounterHandler handler = new CounterHandler();
        handler.attach(res);
        CometEngine engine = CometEngine.getEngine();
        CometContext<?> context = engine.getCometContext(contextPath);
        context.addCometHandler(handler);
    }
}
