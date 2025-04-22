/**
 *  RUN parancs implementációja.
 */
public class RunCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public RunCommand(InputCommand inputCommand, Player actor) {
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
            CommandReader commandReader = (CommandReader)handler;
            commandReader.bufferFile(input.params()[0]);
        } catch (Exception e) {
            System.out.println(commandErrorMessage);
        }
    }
}
