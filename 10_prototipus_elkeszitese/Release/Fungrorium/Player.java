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
}
