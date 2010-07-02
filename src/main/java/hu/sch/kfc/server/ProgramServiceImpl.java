package hu.sch.kfc.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import hu.sch.kfc.client.service.ProgramService;
import hu.sch.kfc.server.domain.Message;
import hu.sch.kfc.shared.Program;
import hu.sch.kfc.shared.Group;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProgramServiceImpl extends RemoteServiceServlet implements ProgramService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("KFC-PU");
    private EntityManager em = emf.createEntityManager();
    
    @Override
    public List<Program> getEventsForGroup(Group g) {
        
        Message m = em.find(Message.class, 1L);
        System.err.println(m==null? "null" : m.toString());
        
        List<Program> events = new ArrayList<Program>(2);

        Calendar c = Calendar.getInstance();
        c.set(2010, 6, 23, 13, 00);

        Date start = c.getTime();
        c.add(Calendar.HOUR_OF_DAY, 3);
        Date end = c.getTime();

        events.add(new Program(start, end, "Próba event",
                "Aliquam odio turpis, consequat ac condimentum at, ultricies et dui."
                        + "Mauris fringilla mauris ut diam dapibus pulvinar. Fusce ac"
                        + " dolor urna. Integer euismod pulvinar sollicitudin. Integer"
                        + " egestas malesuada leo a rhoncus. Curabitur lorem orci, vene"
                        + "natis eleifend condimentum sit amet, malesuada vel ante. Done"
                        + "c eu sodales libero. Sed sed neque eros. Maecenas nec orci pu"
                        + "rus. Praesent imperdiet est lorem. Quisque aliquam, dolor vit"
                        + "ae sagittis semper, massa massa congue lectus, ut placerat mas"
                        + "sa magna sit amet orci. Curabitur fringilla, est sit amet port"
                        + "titor ultrices, urna turpis pulvinar nisl, non blandit turpis "
                        + "lacus quis neque. Vestibulum dolor dui, tristique vitae imperdi"
                        + "et id, fermentum sit amet nunc. Lorem ipsum dolor sit amet, con. "));

        events.add(new Program(start, end, "Próba event2",
                "Aliquam odio turasdfasdfpis, consequat ac condimentum at, ultricies et dui."
                        + "Mauris fringilla mauris ut diam sdfasdfsadfod pulvinar sollicitudin. Integer"
                        + " egestas malesuada leo sadf asdf sit amet, malesuada vel ante. Done"
                        + "c eu sodales libero. Seasdf asdfa sdflvinar nisl, non blandit turpis "
                        + "lacus quis neque. Vestibulum dolor dui, tristique vitae imperdi"
                        + "et id, fermentum sit amet nunc. Lorem ipsum dolor sit amet, con. "));

        return events;
    }

    @Override
    public List<Program> getEventsForGroupToken(String token) {
        return getEventsForGroup(null);
    }
}
