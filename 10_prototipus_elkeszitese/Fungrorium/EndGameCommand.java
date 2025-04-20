/**
 * END_GAME parancs implementációja.
 */
public class EndGameCommand extends CommandImpl {

    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public EndGameCommand(InputCommand inputCommand, Player actor) {
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
            GameEndManager gameEndManager = (GameEndManager) handler;
            gameEndManager.showWinners();
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
