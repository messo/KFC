FAQ
===

1. Hogy lehet tesztelni?
------------------------

  Nagyon egyszerű, írjuk be a konzolba, hogy:
    $ mvn compile war:exploded gwt:run
  Ekkor a következő történik: lefordulnak az osztályok, de előtte a gwt:compile is meghívódik, ami
  generál nekünk Async osztályokat is. Ezután a war:exploded bemásolja a target/KFC-<verzió>/
  mappába a src/main/webapp/-ben lévő dolgokat, tehát a target/KFC-<verzió>/ mappában már egy működő
  alkalmazás található. Innen futtatja az alkalmazásunkat a gwt:run

2. Hogy tudom kitolni saját webszerverre?
-----------------------------------------

  A konzolba írjuk be, hogy:
    $ mvn package
  Ekkor a target/KFC-<verzió>/ mappában lesz egy war csomag. Ezt deployoljuk a webszerverünkre és voilà.

3. Okés, tudom tesztelni, de hogy tudok rendesen fejleszteni?
-------------------------------------------------------------

  A projekt Eclipse-ready (3.5 Galileo + Google Eclipse Plugin), tehát nincs más dolgod, mint az egészet
  beimportálni a workspace-edbe. Utána jobb klikk a KFC.launch-on Run As > KFC. Ekkor elindul az Eclipsen
  belül a Development Mode, ahol nyomon lehet követni az eseményeket, nem mellesleg, ha módosítás történik
  a kliens rétegben, akkor a böngészőn belül csak frissíteni kell és máris éles a dolog. Ha servleteken
  történik a módosítás, akkor meg a Development Mode-ban a kis frissítés ikonra (Reload web server) kell
  nyomni (TODO: ez egyelőre nem tudom miért, de nem működik. Le kell állítani a dev mode-ot és újra el kell
  indítani, hogy a szervletek is frissüljenek).
