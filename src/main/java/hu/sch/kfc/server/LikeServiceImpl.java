package hu.sch.kfc.server;

import hu.sch.kfc.client.service.LikeService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LikeServiceImpl extends RemoteServiceServlet implements LikeService {

    private int like = 0;

    // private List<CometSession> cometSessions = Collections.synchronizedList(new
    // ArrayList<CometSession>());

    @Override
    public void like() {

    }

}
