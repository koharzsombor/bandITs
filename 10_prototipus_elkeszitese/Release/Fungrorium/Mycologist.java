/**
 * Egy rovarászt ábráoló osztály.
 */
public interface Mycologist extends Player {
    /**
     * Hozzáad egy gombafonalat a gombász saját gombafonalaihoz.
     * @param mycelium A játékos új gombafonala.
     */
    void addMycelium(Mycelium mycelium);

    /**
     * Elvesz egy gombafonalat a gombásztól.
     * @param mycelium A játékostól elvett gombafonál.
     */
    void removeMycelium(Mycelium mycelium);

    /**
     * Megmondja, hogy a játékoshoz tartozik-e egy gombafonál.
     * @param mycelium A gombafonál ami az eldöntés tárgya.
     * @return A játékosé-e a gombafonál.
     */
    boolean ownsMycelium(Mycelium mycelium);

    /**
     * Hozzáad egy gombatestet a gombász saját gombafonalaihoz.
     * @param mushroomBody A játékos új gombateste.
     */
    void addMushroomBody(MushroomBody mushroomBody);

    /**
     * Elvesz egy gombatestet a gombásztól.
     * @param mushroomBody A játékostól elvett gombatest.
     */
    void removeMushroomBody(MushroomBody mushroomBody);

    /**
     * Megmondja, hogy a játékoshoz tartozik-e egy gombatest.
     * @param mushroomBody A gombatest ami az eldöntés tárgya.
     * @return A játékosé-e a gombatest.
     */
    boolean ownsMushroomBody(MushroomBody mushroomBody);

    /**
     * Visszadja a maradék növesztések számát.
     * @return A maradék növesztések száma.
     */
    int getRemainingGrows();

    /**
     * Felhasznál 1 növesztési lehetőséget.
     */
    void useGrow();
}
