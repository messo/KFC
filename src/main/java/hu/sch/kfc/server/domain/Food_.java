package hu.sch.kfc.server.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-01-15T15:03:54.932+0100")
@StaticMetamodel(Food.class)
public class Food_ {
	public static volatile SingularAttribute<Food, Long> id;
	public static volatile SingularAttribute<Food, Group> producer;
	public static volatile ListAttribute<Food, Program> programs;
}
