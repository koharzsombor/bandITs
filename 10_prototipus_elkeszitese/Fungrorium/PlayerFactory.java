/**
 * Játékosokat példányosító osztály.
 */
public interface PlayerFactory {
    /**
     * Példányosít egy játékost a megadott paraméterek alapján.
     * @param type A játékos típusa.
     * @param name A játékos neve.
     * @return Az új játékos.
     */
    Player create(String type, String name);
}
