/**
 * GROW_MUSHROOMBODY parancs implementációja.
 */
public class GrowMushroomBodyCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public GrowMushroomBodyCommand(InputCommand inputCommand, Player actor) {
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
            Tecton tecton = (Tecton)ObjectRegistry.getObject(input.params()[1]);
            Mycologist mycologist = (Mycologist)actingPlayer;
            controller.growMushroomBody(input.params()[0], tecton, mycologist);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
