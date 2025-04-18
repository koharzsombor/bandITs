public abstract class CommandImpl implements Command {
    /**
     * Az input, ami a parancs alapjaként szolgál.
     */
    protected final InputCommand input;

    /**
     * A játékos aki a parancsot kiadta.
     * Ez nem minden parancsnál releváns.
     */
    protected final Player actingPlayer;

    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor A parancsot végrehajtó
     */
    public CommandImpl(InputCommand inputCommand, Player actor) {
        input = inputCommand;
        actingPlayer = actor;
    }
}
