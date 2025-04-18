/**
 * Egy végrehajtható parancs.
 */
public interface Command {
    /**
     * A parancs végrehajtása.
     * @param handler A parancsot végrehajtó kezelő.
     */
    void execute(CommandHandler handler);
}
