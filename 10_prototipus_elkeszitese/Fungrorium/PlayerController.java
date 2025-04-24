/**
 * A játékosok létrehozásáért felelős osztály interfésze.
 */
public interface PlayerController extends CommandHandler {
    /**
     * Hozzáad egy játékost a játékhoz.
     * @param type Játékos típusa.
     * @param name Játékos neve.
     */
    void createPlayer(String type, String name);
}
