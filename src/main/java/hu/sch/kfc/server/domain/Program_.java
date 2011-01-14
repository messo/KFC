package hu.sch.kfc.server.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-01-14T20:11:28.110+0100")
@StaticMetamodel(Program.class)
public class Program_ {
	public static volatile SingularAttribute<Program, Long> id;
	public static volatile SingularAttribute<Program, String> name;
	public static volatile SingularAttribute<Program, String> description;
	public static volatile SingularAttribute<Program, Group> organizer;
	public static volatile SingularAttribute<Program, Date> start;
	public static volatile SingularAttribute<Program, Date> end;
	public static volatile SingularAttribute<Program, Date> orderStart;
	public static volatile SingularAttribute<Program, Date> orderEnd;
	public static volatile SingularAttribute<Program, Integer> version;
}
