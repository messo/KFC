package hu.sch.kfc.client.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ezzel kell megannotálni egy metódust a {@link CachingRemoteService} interfészekben, hogy érvénybe
 * lépjen a cache. Emellett még az is szükséges, hogy a metódus visszatérési értéke implementálja a
 * {@link Cacheable} interfészt, illetve ekkor az első paraméter az objektum cache szerinti kulcsa
 * legyen (így tudjuk lekérdezni a cacheből, hogy szerepel-e az objektum).
 * <p>
 * Ne felejtsük el, hogy ilyenkor a {@link CachingAsyncCallback} interfészt használjuk az
 * AsyncCallback helyett, hiszen így kapunk teljes értékű cache-mechanizmust.
 * </p>
 * 
 * @author messo
 * @since 0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseCache {

}
