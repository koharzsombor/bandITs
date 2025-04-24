/**
 * EJECT_SPORES parancs implementációja.
 */
public class EjectSporesCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public EjectSporesCommand(InputCommand inputCommand, Player actor) {
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

            Mycologist mycologist = (Mycologist)actingPlayer;
            if (!mycologist.ownsMushroomBody(mushroomBody))
                return;

            Tecton tecton = (Tecton)ObjectRegistry.getObject(input.params()[1]);
            mushroomBodyController.eject(mushroomBody, tecton);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
