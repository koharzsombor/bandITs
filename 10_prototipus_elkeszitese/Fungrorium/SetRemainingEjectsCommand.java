/**
 * SET_REMAININGEJECTS parancs implementációja.
 */
public class SetRemainingEjectsCommand extends CommandImpl {

    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public SetRemainingEjectsCommand(InputCommand inputCommand, Player actor) {
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
            MushroomBodyController controller = (MushroomBodyController)handler;
            MushroomBody mushroomBody = (MushroomBody)ObjectRegistry.getObject(input.params()[0]);
            int amount = Integer.parseInt(input.params()[1]);
            controller.setRemainingEjects(mushroomBody, amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
