/**
 * A játékosok létrehozását irányító objektum.
 */
public class PlayerControllerImpl implements PlayerController {

    /**
     * A játékosokat tároló objektum.
     */
    private PlayerContainer playerContainer;

    /**
     * A játékosokat példányosító objektum.
     */
    private PlayerFactory playerFactory;

    /**
     * A létrehozáshoz szükséges egy tároló objektum.
     * @param container A tároló objektum.
     */
    public PlayerControllerImpl(PlayerContainer container) {
        playerContainer = container;
        playerFactory = new PlayerFactoryImpl();
    }

    /**
     * Hozzáad egy játékost a játékhoz.
     *
     * @param type Játékos típusa.
     * @param name Játékos neve.
     */
    @Override
    public void createPlayer(String type, String name) {
        Player newPlayer = playerFactory.create(type, name);
        playerContainer.addPlayer(newPlayer, type);
        playerContainer.getNextPlayer();
    }
}
