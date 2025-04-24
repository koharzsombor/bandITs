/**
 * ADD_NEIGHBOUR parancs implementációja.
 */
public class AddNeighbourCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public AddNeighbourCommand(InputCommand inputCommand, Player actor) {
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
            Tecton tecton1 = (Tecton)ObjectRegistry.getObject(input.params()[0]);
            Tecton tecton2 = (Tecton)ObjectRegistry.getObject(input.params()[1]);

            tectonController.addNeighbour(tecton1, tecton2);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
