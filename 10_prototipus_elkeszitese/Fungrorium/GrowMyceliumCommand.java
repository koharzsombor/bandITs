/**
 * GROW_MYCELIUM parancs implementációja.
 */
public class GrowMyceliumCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public GrowMyceliumCommand(InputCommand inputCommand, Player actor) {
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
            GrowthController controller = (GrowthController)handler;
            Tecton location = (Tecton)ObjectRegistry.getObject(input.params()[2]);
            Mycologist mycologist = (Mycologist)actingPlayer;

            controller.growMycelium(input.params()[0], input.params()[1], location, mycologist);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
