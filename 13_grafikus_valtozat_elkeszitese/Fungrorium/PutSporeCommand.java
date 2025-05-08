/**
 * PUT_SPORE parancs implementációja.
 */
public class PutSporeCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public PutSporeCommand(InputCommand inputCommand, Player actor) {
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
            Tecton tecton = (Tecton)ObjectRegistry.getObject(input.params()[2]);
            tectonController.putSpore(input.params()[0], input.params()[1], tecton);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
