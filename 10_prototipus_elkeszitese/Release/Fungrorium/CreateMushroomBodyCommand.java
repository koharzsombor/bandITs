public class CreateMushroomBodyCommand extends CommandImpl {

    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public CreateMushroomBodyCommand(InputCommand inputCommand, Player actor) {
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
            MapCreationController controller = (MapCreationController)handler;
            Tecton tecton = (Tecton)ObjectRegistry.getObject(input.params()[1]);
            Mycologist currentPlayer = actingPlayer == null ? null : (Mycologist)actingPlayer;

            controller.createMushroomBody(tecton, input.params()[0], currentPlayer);
        } catch (Exception e) {
            System.out.println("Command Syntax Error: Invalid Parameters");
        }
    }
}
