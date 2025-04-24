/**
 * STATE parancs implementációja.
 */
public class StateCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public StateCommand(InputCommand inputCommand, Player actor) {
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
            TraceablePrinter printer = (TraceablePrinter)handler;
            Object queriedObject = ObjectRegistry.getObject(input.params()[0]);
            printer.print(queriedObject.toString());
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
