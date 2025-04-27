import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A győztest eldöntő osztály implementációja.
 */
public class ScoreCalculatorImpl implements ScoreCalculator {
    /**
     * Eldönti, hogy ki nyert, az adott játékosok között.
     *
     * @param candidates A játékosok.
     * @return A nyertes játékosok.
     */
    @Override
    public Iterable<Player> determineWinner(Iterable<Player> candidates) {
        int maxScore = 0;
        for (Player candidate : candidates) {
            int score = candidate.calculateScore();
            if (score > maxScore) {
                maxScore = score;
            }
        }

        List<Player> winners = new LinkedList<>();

        for (Player candidate : candidates) {
            if (candidate.calculateScore() == maxScore) {
                winners.add(candidate);
            }
        }

        return winners;
    }
}
