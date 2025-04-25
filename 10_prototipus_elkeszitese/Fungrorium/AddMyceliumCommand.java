/**
 * ADD_MYCELIUM parancs implementációja.
 */
public class AddMyceliumCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public AddMyceliumCommand(InputCommand inputCommand, Player actor) {
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
            TectonController tectonController = (TectonController)handler;
            Mycelium mycelium = (Mycelium)ObjectRegistry.getObject(input.params()[0]);
            Tecton tecton = (Tecton)ObjectRegistry.getObject(input.params()[1]);
            tectonController.addMycelium(mycelium, tecton);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
