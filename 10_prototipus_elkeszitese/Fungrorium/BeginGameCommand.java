/**
 * BEGIN_GAME parancs implementációja.
 */
public class BeginGameCommand extends CommandImpl {

    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public BeginGameCommand(InputCommand inputCommand, Player actor) {
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
            controller.beginFirstTurn();
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
