/**
 * CUT parancs implementációja.
 */
public class CutCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public CutCommand(InputCommand inputCommand, Player actor) {
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
            InsectController controller = (InsectController)handler;
            Insect insect = (Insect)ObjectRegistry.getObject(input.params()[0]);
            Entomologist entomologist = (Entomologist)actingPlayer;

            if (!entomologist.ownsInsect(insect))
                return;

            controller.cut(insect);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
