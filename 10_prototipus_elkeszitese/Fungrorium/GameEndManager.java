/**
 * A játék kezdését és befejezését irányító osztály.
 */
public interface GameEndManager extends RoundBeginSubscriber {
    /**
     * Beállítja a játék hosszát.
     * @param newLength Ahány körig tart a játék.
     */
    void setGameLength(int newLength);

    /**
     * Visszadja a játék hosszát.
     * @return A játék hossza.
     */
    int getGameLength();

    /**
     * Kiírja a játék győzteseit.
     */
    void showWinners();
}
