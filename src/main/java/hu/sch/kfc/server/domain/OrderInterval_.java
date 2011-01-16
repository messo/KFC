package hu.sch.kfc.server.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-01-16T11:26:35.079+0100")
@StaticMetamodel(OrderInterval.class)
public class OrderInterval_ {
	public static volatile SingularAttribute<OrderInterval, Long> id;
	public static volatile SingularAttribute<OrderInterval, Program> program;
	public static volatile SingularAttribute<OrderInterval, Date> start;
	public static volatile SingularAttribute<OrderInterval, Date> end;
	public static volatile SingularAttribute<OrderInterval, Integer> limit;
	public static volatile SingularAttribute<OrderInterval, Integer> count;
}
