package hu.sch.kfc.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
    private static EntityManagerFactory instance = Persistence.createEntityManagerFactory("KFC-PU");

    public static EntityManager get() {
        return instance.createEntityManager();
    }
}
