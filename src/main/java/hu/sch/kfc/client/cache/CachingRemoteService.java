package hu.sch.kfc.client.cache;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Jelölő interfész, melyből azokat az RPC interfészeket kell származtatni, melyeknél szeretnénk
 * cache-mechanizmust használni.
 * <p>
 * Ezen felül a kérdéses metódusokat annotálni kell a {@link UseCache @UseCache}-el, továbbá a
 * metódus visszatérési értékének implementálnia kell a {@link Cacheable} interfészt, illetve amikor
 * meg akarnánk valósítani az interfész aszinkron párjánál az
 * {@link com.google.gwt.user.client.rpc.AsyncCallback AsyncCallback}et, akkor ne tegyük ezt, hanem
 * használjuk a {@link CachingAsyncCallback} interfészt, hiszen így lesz teljes értékű a mechanizmus
 * (ez teszi be a cachebe az objektumot, enélkül hiába akarjuk kiolvasni, hiszen sose kerül bele).
 * </p>
 * 
 * @author messo
 * @since 0.1
 */
public interface CachingRemoteService extends RemoteService {

}
