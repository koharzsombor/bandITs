/**
 * A játék befezésért felelős osztály implementációja.
 */
public class GameEndManagerImpl implements GameEndManager {

    /**
     * A játék maradék körei.
     */
    private int gameLength = -1;

    /**
     * A játékosok pontszámát kiszámoló objektum.
     */
    private ScoreCalculator scoreCalculator;

    /**
     * Egy visszaolvasható nyomtató, a győztesek kiírásához.
     */
    private TraceablePrinter printer;

    private PlayerContainer container;

    /**
     * A létrehozáshoz szükséges egy visszaolvasható mogger osztály.
     * @param traceablePrinter
     */
    public GameEndManagerImpl(TraceablePrinter traceablePrinter, PlayerContainer playerContainer) {
        scoreCalculator = new ScoreCalculatorImpl();
        printer = traceablePrinter;
        container = playerContainer;
    }

    /**
     * Beállítja a játék hosszát.
     *
     * @param newLength Ahány körig tart a játék.
     */
    @Override
    public void setGameLength(int newLength) {
        gameLength = newLength;
    }

    /**
     * Visszadja a játék hosszát.
     *
     * @return A játék hossza.
     */
    @Override
    public int getGameLength() {
        return gameLength;
    }

    /**
     * Kiírja a játék győzteseit.
     */
    @Override
    public String showWinners() {
        /*
        StringBuilder stringBuilder = new StringBuilder();

        printer.printLine("WINNERS:");
        printer.printLine("MYCOLOGIST:");

        Iterable<Player> mycologistWinners = scoreCalculator.determineWinner(container.getMycologists());
        for (Player winner : mycologistWinners) {
            printer.printLine(winner.getName());
        }

        printer.printLine("ENTOMOLOGIST:");

        Iterable<Player> entomologistWinners = scoreCalculator.determineWinner(container.getEntomologists());
        for (Player winner : entomologistWinners) {
            printer.printLine(winner.getName());
        }
        */

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<div style='text-align:center;'>");

        stringBuilder.append("WINNERS:<br><br>");
        stringBuilder.append("MYCOLOGIST:<br>");
        Iterable<Player> mycologistWinners = scoreCalculator.determineWinner(container.getMycologists());
        for (Player winner : mycologistWinners) {
            stringBuilder.append(winner.getName()).append("<br>");
        }
        stringBuilder.append("<br>");
        stringBuilder.append("ENTOMOLOGIST:<br>");
        Iterable<Player> entomologistWinners = scoreCalculator.determineWinner(container.getEntomologists());
        for (Player winner : entomologistWinners) {
            stringBuilder.append(winner.getName()).append("<br>");
        }
        stringBuilder.append("<br>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }

    @Override
    public void onRoundBegin() {
        if (gameLength == -1)
            return;

        gameLength--;

        if (gameLength <= 0) {
            ViewRepository.updateObject(this);
            //showWinners();
        }
    }

    @Override
    public String toString() {
        if (gameLength == -1) {
            return "Még a játék nem kezdődött el";
        }
        return "Még " + gameLength + " kör van hátra";
    }
}
