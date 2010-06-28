package hu.sch.kfc.server;

import java.util.ArrayList;
import java.util.List;
import hu.sch.kfc.client.service.GroupService;
import hu.sch.kfc.shared.Group;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GroupServiceImpl extends RemoteServiceServlet implements GroupService {

    private static final Group pizzasch = new Group("Pizzásch", "pizzasch");
    private static final Group gyrososch = new Group("Gyrososch", "gyrososch");

    @Override
    public List<Group> getGroups() {
        List<Group> list = new ArrayList<Group>(2);
        list.add(pizzasch);
        list.add(gyrososch);
        return list;
    }

    @Override
    public Group getGroupByToken(String token) {
        if (token.equals(pizzasch.getToken())) {
            return pizzasch;
        } else if(token.equals(gyrososch.getToken())) {
            return gyrososch;
        }
        return null;
    }
}
