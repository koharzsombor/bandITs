/**
 * DEACTIVATE parancs implementációja.
 */
public class DeactivateCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public DeactivateCommand(InputCommand inputCommand, Player actor) {
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
            MushroomBodyController mushroomBodyController = (MushroomBodyController)handler;
            MushroomBody mushroomBody = (MushroomBody)ObjectRegistry.getObject(input.params()[0]);
            mushroomBodyController.deactivate(mushroomBody);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
