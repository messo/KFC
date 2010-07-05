package hu.sch.kfc.client.cache;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Cache mechanizmussal ellátott {@link AsyncCallback} implementáció. Lényegében az történik, hogy
 * az {@link AsyncCallback#onSuccess(Object)} metódust véglegesítjük olyan formában, hogy a szerver
 * által visszaküldött adatot a cachebe mentjük ({@link Cache#put(Cacheable)}), majd meghívjuk a
 * {@link CachingAsyncCallback#onRetrieved(Cacheable)} metódust, amit meg kell valósítani.
 * 
 * @param <T>
 *            az objektum típusa, amit cachelni szeretnénk
 * @author messo
 * @since 0.1
 * @see Cache
 * @see Cacheable
 */
public abstract class CachingAsyncCallback<T extends Cacheable<T>> implements AsyncCallback<T> {

    /**
     * Ha sikeres a callback, akkor mindenképp mentsük el a cachebe, majd hívjuk meg a
     * {@link CachingAsyncCallback#onRetrieved(Cacheable)} metódust.
     */
    public final void onSuccess(T obj) {
        Cache.put(obj);
        onRetrieved(obj);
    };

    /**
     * Ez hívódik meg, ha megvan az adat (akár cacheből, akár nem), lényegében ugyanaz a szerepe,
     * mint az {@link AsyncCallback#onSuccess(Object)}, csak ennek más néven kell futnia.
     * 
     * @param obj
     *            a megszerzett objektum
     */
    public abstract void onRetrieved(T obj);
}
