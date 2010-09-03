FAQ
===

1. Hogy lehet tesztelni?
------------------------

  Nagyon egyszerű, írjuk be a konzolba, hogy:
    $ mvn war:exploded gwt:run
  Ekkor a következő történik: a `war:exploded` bemásolja a target/KFC-<verzió>/ mappába a
  src/main/webapp/-ben lévő dolgokat, majd a `gwt:run` futtatja az egészet, persze előtte lefordítja,
  amit le kell fordítani ;)

2. Hogy tudom kitolni saját webszerverre?
-----------------------------------------

  A konzolba írjuk be, hogy:
    $ mvn package
  Ekkor a target/KFC-<verzió>/ mappában lesz egy war csomag. Ezt deployoljuk a webszerverünkre és voilà.
  
  Megj: Jelenleg az egész Jetty-hez van kötve a jetty-env.xml miatt, ha felvesztek egy ilyen resource-t
  a célkonténeren is, akkor szerintem nem lehet para.

3. Okés, tudom tesztelni, de hogy tudok rendesen fejleszteni?
-------------------------------------------------------------

  A projekt Eclipse-ready (3.6 Helios + Google Eclipse Plugin), tehát nincs más dolgod, mint az egészet
  beimportálni a workspace-edbe. Utána jobb klikk a KFC.launch-on Run As > KFC. Ekkor elindul az Eclipsen
  belül a Development Mode, ahol nyomon lehet követni az eseményeket, nem mellesleg, ha módosítás történik
  a kliens rétegben, akkor a böngészőn belül csak frissíteni kell és máris éles a dolog. Ha servleteken
  történik a módosítás, akkor meg a Development Mode-ban a kis frissítés ikonra (Reload web server) kell
  nyomni. (Ez jelenleg nem működik, mert a jetty kivételt dob: NPE @ org.mortbay.jetty.plus.webapp.AbstractConfiguration.callPreDestroyCallbacks())
  
  Egyelőre a 3.6-os Eclipse-el és a hozzá tartozó pluginnal nem akar működni rendesen, nem akarja a
  src/main/webapp tartalmát bemásolni a helyére, így csak akkor megy rendesen ha tolunk egy `mvn war:exploded`-et.
  Tehát, ha véletlen változtatunk a css-ben, vagy valamelyik fájlban a src/main/webapp alatt, akkor futtassuk le
  az előző parancsot és mehet a móka.

4. Leszedtem a repóból, megnyitom Eclipse-ben és panaszkodik, hogy: "Missing asynchronous interface ..."
--------------------------------------------------------------------------------------------------------

  Igen, ezeket először le kell generálni (a gwt-maven-plugin segítségével, így az amúgy könnyen írható 
  async interfészekkel nem kell törődnünk, azokat tudjuk generáltatni), a következő paranccsal:
    $ mvn gwt:generateAsync
  ezután már ott kell lenniük a helyükön a fájloknak.
