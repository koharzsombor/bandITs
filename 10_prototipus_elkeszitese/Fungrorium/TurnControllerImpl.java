/**
 * A köröket irányító osztály implementációja.
 */
public class TurnControllerImpl implements TurnController {
    /**
     * A játékosok tárolója.
     */
    private PlayerContainer playerContainer;

    /**
     * A létrehozáshoz szükséges megadni egy játékos tárolót.
     * @param container A tároló.
     */
    public TurnControllerImpl(PlayerContainer container) {
        playerContainer = container;
    }

    /**
     * Befejezi a jelenlegi kört.
     */
    @Override
    public void endTurn() {
        Player nextPlayer = playerContainer.getNextPlayer();
        nextPlayer.notifySubscribers();
    }

    /**
     * Visszadja a játékost, akinek jelenleg köre van.
     *
     * @return A jelenlegi játékos.
     */
    @Override
    public Player getCurrentPlayer() {
        return playerContainer.getCurrentPlayer();
    }

    /**
     * A játék első körét elkezdi.
     */
    @Override
    public void beginFirstTurn() {
        playerContainer.resetCurrentPlayer();
        playerContainer.getCurrentPlayer().notifySubscribers();
    }
}
