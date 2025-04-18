/**
 * Játékosokat tároló ostály.
 */
public interface PlayerContainer {
    /**
     * Hozzáad egy játékost a tárolóhoz.
     * @param player A játékos, akit hozzá szeretnénk adni.
     * @param type A játékos típusa.
     */
    void addPlayer(Player player, String type);

    /**
     * Kivesz egy játékost a tárolóból.
     * @param player A játékost akit ki szeretnénk venni.
     */
    void removePlayer(Player player);

    /**
     * A jelenlegi játékos a következő lesz. És visszadja a következő játékost.
     * @return A következő játékos.
     */
    Player getNextPlayer();

    /**
     * A mostani játékost visszadja.
     * @return A mostani játékos.
     */
    Player getCurrentPlayer();

    /**
     * Visszaadja az összes játékost.
     * @return Az összes játékos.
     */
    Iterable<Player> getPlayers();

    /**
     * Visszadja az összes gombászt.
     * @return Az összes gombász.
     */
    Iterable<Player> getMycologists();

    /**
     * Visszadja az összes rovarászt.
     * @return Az összes rovarász.
     */
    Iterable<Player> getEntomologists();
}
