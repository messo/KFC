package hu.sch.kfc.client.util;

import java.util.Date;

public class DateHolder {

    /**
     * Olyanra kéne megcsinálni, hogy az elején lekérjük a szervertől az időt
     * és onnantól kezdve már mi számolunk.
     * 
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }
}
