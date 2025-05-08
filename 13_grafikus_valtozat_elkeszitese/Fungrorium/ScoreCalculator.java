/**
 * Azaz osztály, ami eldönti, hogy a játékosok közül ki nyert.
 */
public interface ScoreCalculator {
    /**
     * Eldönti, hogy ki nyert, az adott játékosok között.
     * @param candidates A játékosok.
     * @return A nyertes játékosok.
     */
    Iterable<Player> determineWinner(Iterable<Player> candidates);
}
