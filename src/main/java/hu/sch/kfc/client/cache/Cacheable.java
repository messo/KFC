package hu.sch.kfc.client.cache;

/**
 * Cachelhető objektumoknak ezt az interfészt kell implementálniuk.
 * 
 * @param <T>
 *            az objektum típusa, amit cachelhetővé szeretnénk tenni
 * @author messo
 * @since 0.1
 */
public interface Cacheable<T> {

    /**
     * Objektumhoz tartozó kulcs lekérdezése
     * 
     * @return az objektum kulcsa, ami alapján a cacheből visszakereshető
     */
    String getKey();

    /**
     * Akkor hívódik meg, amikor már van ilyen kulcsú objektum a cacheben, lehetőséget adva arra,
     * hogy a már bent lévő objektum bizonyos attribútumait felhasználjuk. Azzal az objektummal
     * térjünk vissza, amit a cacheben látni szeretnénk.
     * 
     * @param other
     *            a már cacheben lévő példány
     * @return a cachebe bekerülő példány
     */
    T merge(T other);
}
