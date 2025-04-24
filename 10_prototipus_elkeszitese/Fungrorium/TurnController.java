/**
 * A körök kezelésért felelős osztály.
 */
public interface TurnController extends CommandHandler, TurnInitializer {
    /**
     * Befejezi a jelenlegi kört.
     */
    void endTurn();

    /**
     * Visszadja a játékost, akinek jelenleg köre van.
     * @return A jelenlegi játékos.
     */
    Player getCurrentPlayer();
}
