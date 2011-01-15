package hu.sch.kfc.server.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-01-15T23:05:34.001+0100")
@StaticMetamodel(Order.class)
public class Order_ {
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, Food> food;
	public static volatile SingularAttribute<Order, User> user;
	public static volatile SingularAttribute<Order, OrderInterval> interval;
}
