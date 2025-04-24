/**
 * A SET_BREAKTIMER parancs implementációja.
 */
public class SetBreakTimerCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public SetBreakTimerCommand(InputCommand inputCommand, Player actor) {
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
            TectonController controller = (TectonController)handler;
            Tecton tecton = (Tecton)ObjectRegistry.getObject(input.params()[0]);

            controller.setBreakTimer(tecton, Integer.parseInt(input.params()[1]));
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
