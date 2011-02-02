package hu.sch.kfc.server;

import hu.sch.kfc.domain.Group;
import hu.sch.kfc.domain.OrderInterval;
import hu.sch.kfc.domain.Program;
import hu.sch.kfc.ejb.GroupManager;
import hu.sch.kfc.ejb.ProgramManager;
import hu.sch.kfc.misc.AbstractEntity;
import com.google.gwt.requestfactory.shared.Locator;

public class EntityLocator extends Locator<AbstractEntity, Long> {

    @Override
    public AbstractEntity create(Class<? extends AbstractEntity> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public AbstractEntity find(Class<? extends AbstractEntity> clazz, Long id) {
        if (clazz.equals(Group.class)) {
            return BeanLocator.lookupBean(GroupManager.class).findGroup(id);
        } else if (clazz.equals(Program.class)) {
            return BeanLocator.lookupBean(ProgramManager.class).findProgram(id);
        } else if (clazz.equals(OrderInterval.class)) {
            return BeanLocator.lookupBean(ProgramManager.class).findOrderInterval(id);
        }
        return null;
    }

    @Override
    public Class<AbstractEntity> getDomainType() {
        // TODO wtf? ez minek?
        return AbstractEntity.class;
    }

    @Override
    public Long getId(AbstractEntity e) {
        return e.getId();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public Object getVersion(AbstractEntity e) {
        return e.getVersion();
    }
}
