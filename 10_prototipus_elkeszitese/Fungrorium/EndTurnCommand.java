import java.util.logging.Handler;

/**
 * END_TURN implementációja.
 */
public class EndTurnCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public EndTurnCommand(InputCommand inputCommand, Player actor) {
        super(inputCommand, actor);
    }

    /**
     * A parancs végrehajtása.
     *
     * @param handler A parancsot végrehajtó kezelő.
     */
    @Override
    public void execute(CommandHandler handler) {
        try {
            TurnController controller = (TurnController)handler;
            controller.endTurn();
        } catch (Exception e) {
            System.out.println("Command Syntax Error: ");
        }
    }
}
