package hu.sch.kfc.server.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2010-09-03T13:56:57.771+0200")
@StaticMetamodel(GroupEntity.class)
public class GroupEntity_ {
	public static volatile SingularAttribute<GroupEntity, Long> id;
	public static volatile SingularAttribute<GroupEntity, String> token;
	public static volatile SingularAttribute<GroupEntity, String> name;
	public static volatile ListAttribute<GroupEntity, ProgramEntity> programs;
}
