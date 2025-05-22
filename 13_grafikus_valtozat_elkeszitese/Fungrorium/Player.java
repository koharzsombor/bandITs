/**
 * Egy játékost ábrázoló osztály.
 */
public interface Player {
    /**
     * Egy feliratkozó jelzi, hogy értesítést szeretne kapni arról, hogy a játékos köre elkezdődött.
     * @param subscriber
     */
    void subscribe(TurnBeginSubscriber subscriber);

    /**
     * Beállítja a játékos nevét.
     * @param name A játékos neve.
     */
    void setName(String name);

    /**
     * Visszadja a játékos nevét.
     * @return A játékos neve.
     */
    String getName();

    /**
     * Szól a játékos feliratkozóinak, hogy a játékosnak elkezdődött a köre.
     */
    void notifySubscribers();

    /**
     * Kiszámolja a játékos pontszámát.
     * @return A játékos pontszáma.
     */
    int calculateScore();

    /**
     * Megmondja, hogy a játékos tudja-e irányítani az adott gombatestet.
     *
     * @param mushroomBody Az adott gombatest.
     * @return A játékos tudja-e irányítani az adott gombatestet.
     */
    boolean controlsMushroomBody(MushroomBody mushroomBody);

    /**
     * Megmondja, hogy a játékos tudja-e irányítani az adott gombafonalat.
     *
     * @param mycelium Az adott gombafonál.
     * @return A játékos tudja-e irányítani az adott gombafonalat.
     */
    boolean controlsMycelium(Mycelium mycelium);

    /**
     * Megmondja, hogy a játékos tudja-e irányítani az adott rovart.
     *
     * @param insect Az adott rovar.
     * @return A játékos tudja-e irányítani az adott rovart.
     */
    boolean controlsInsect(Insect insect);
}
