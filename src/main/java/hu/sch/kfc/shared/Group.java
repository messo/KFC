package hu.sch.kfc.shared;

import hu.sch.kfc.client.cache.Cacheable;
import java.io.Serializable;
import java.util.List;

/**
 * Egy kört reprezentál, amely rendezvényeket szervezhet, és lehet tőlük kaját rendelni.
 * 
 * @author messo
 * @since 0.1
 */
@SuppressWarnings("serial")
public class Group implements Serializable, Cacheable<Group> {

    private String name;
    private String token;
    private List<Program> programs = null;

    public Group() {
    }

    public Group(String name, String token) {
        this.name = name;
        this.token = token;
    }

    /**
     * Kör olvasható nevének lekérdezése
     * 
     * @return kör neve
     */
    public String getName() {
        return name;
    }

    /**
     * Körhöz tartozó token (ami az url-ben szerepel) lekérdezése
     * 
     * @return token
     */
    public String getToken() {
        return token;
    }

    public List<Program> getPrograms() {
        return programs;
    }
    
    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
    
    @Override
    public String getKey() {
        return token;
    }

    @Override
    public Group merge(Group other) {
        List<Program> programs = other.getPrograms();
        // ha nincsenek programok a jelenlegi példányban, de az eddig
        // bent lévőben igen, akkor azt hozzuk át ;)
        if( this.programs == null && programs != null) {
            this.setPrograms(programs);
        }
        return this;
    }
}
