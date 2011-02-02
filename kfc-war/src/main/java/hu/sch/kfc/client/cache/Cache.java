package hu.sch.kfc.client.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Egy nagyon egyszerű cache osztály, amibe be lehet tenni objektumot, és ki lehet olvasni.
 * TODO(messo): rakjunk bele olyat is, hogy valamikor le tud járni az objektum érvényessége.
 * 
 * @author messo
 * @since 0.1
 */
public class Cache {

    private static Map<Class<?>, Map<String, Object>> cache = new HashMap<Class<?>, Map<String, Object>>();
    // private static Map<String, Map<String, Collection<?>>> cacheForCollection = new HashMap<String, Map<String, Collection<?>>>();

    private static <T> Map<String, Object> getCacheForClass(Class<T> clazz) {
        Map<String, Object> cachePerClass = cache.get(clazz);
        if (cachePerClass == null) {
            // ha még nem létezik ehhez a típusú objektumhoz Map, akkor hozzuk létre
            cachePerClass = new HashMap<String, Object>();
            cache.put(clazz, cachePerClass);
        }
        return cachePerClass;
    }

    // Collectionök cacheléséhez.
    // private static <T, C extends Collection<T>> Map<String, Collection<?>>
    // getCollectionCacheForClass(
    // Class<C> cClass, Class<T> tClass) {
    // String key = cClass.getName() + tClass.getName();
    // Map<String, Collection<?>> cachePerClass = cacheForCollection.get(key);
    // if (cachePerClass == null) {
    // // ha még nem létezik ehhez a típusú objektumhoz Map, akkor hozzuk létre
    // cachePerClass = new HashMap<String, Collection<?>>();
    // cacheForCollection.put(key, (Map<String, Collection<?>>) cachePerClass);
    // }
    // return cachePerClass;
    // }

    /**
     * Cacheből lekérünk egy elemet a kulcs alapján. Azért érvényes a T-be kasztolás, mert azon a
     * helyen, csak T típusú elem lehet, lásd {@link Cache#put(Cacheable)}.
     * 
     * @param <T>
     *            az elem típusa, amit le akarunk kérdezni
     * @param clazz
     *            a lekérdezendő elem osztálya
     * @param key
     *            a kulcs, ami alapján keresünk
     * @return a megtalált elem
     * @see Map#get(Object)
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz, String key) {
        return (T) getCacheForClass(clazz).get(key);
    }

    // Collectionök cacheléséhez.
    // public static <C extends Collection<T>, T> C get(Class<C> c, Class<T> t, String key) {
    // return (C) getCollectionCacheForClass(c, t).get(key);
    // }

    /**
     * Beteszünk a cachebe egy elemet.
     * 
     * @param <T>
     *            az elem típusa, amelynek implementálnia kell a {@link Cacheable} interfészt
     * @param obj
     *            az elem, amit beteszünk a cachebe
     * @see Cacheable
     */
    @SuppressWarnings("unchecked")
    public static <T extends Cacheable<T>> void put(T obj) {
        String key = obj.getKey();
        Map<String, Object> cache = getCacheForClass(obj.getClass());
        if (cache.containsKey(key)) {
            // ha már létezik az elem a cacheben, akkor próbáljunk mergeölni
            cache.put(obj.getKey(), obj.merge((T) cache.get(key)));
        } else {
            cache.put(obj.getKey(), obj);
        }
    }

    // Collectionök cacheléséhez.
    // @SuppressWarnings("unchecked")
    // public static <C extends Collection<T>, T> void put(C obj, Class<T> t, String key) {
    // getCollectionCacheForClass(obj.getClass(), t).put(key, obj);
    // }
}
