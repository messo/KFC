package hu.sch.kfc.server.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-01-15T21:55:48.519+0100")
@StaticMetamodel(Group.class)
public class Group_ {
	public static volatile SingularAttribute<Group, Long> id;
	public static volatile SingularAttribute<Group, String> token;
	public static volatile SingularAttribute<Group, String> name;
	public static volatile ListAttribute<Group, Program> programs;
	public static volatile ListAttribute<Group, Food> foods;
	public static volatile SingularAttribute<Group, Integer> version;
}
