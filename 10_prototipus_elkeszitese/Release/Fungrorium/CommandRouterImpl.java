import java.util.HashMap;
import java.util.Map;

/**
 * A parancsokat továbbítió osztály implementációja.
 */
public class CommandRouterImpl implements CommandRouter {

    /**
     * A parancsokat példányosító osztály.
     */
    private CommandFactory factory;

    /**
     * A parancsokat nevükkel tároló tár.
     */
    private final Map<String, CommandHandler> commandRepository = new HashMap<>();

    /**
     * A köröket irányító osztály.
     * Itt azért szükséges, mivel meg tudja mondani, hogy ki a jelenlegi játékos.
     */
    private TurnController turnController;

    /**
     * A létrehozáshoz szükséges a kör kontroller.
     * @param turnController A kör kontroller, hogy biztosra lehessen tudni, hogy melyik
     *                       játékos körében lett a parancs kiadva.
     */
    public CommandRouterImpl(TurnController turnController) {
        factory = new CommandFactoryImpl();
        this.turnController = turnController;
    }

    /**
     * Továbbküld egy megfelelő kezelőnek egy bemeneti parancsot.
     *
     * @param command A parancs bemenete.
     */
    @Override
    public void routeCommand(InputCommand command) {
        CommandHandler handler = commandRepository.get(command.commandName());
        Command execuatbleCommand = factory.createCommand(command, turnController.getCurrentPlayer());

        if (execuatbleCommand == null) {
            System.out.println("Parancs neve nem ismerhető fel!");
            return;
        }

        if (handler == null)
            execuatbleCommand.execute(null);
        else
            handler.handleCommand(execuatbleCommand);
    }

    /**
     * Egy parancs névhez rendel egy kezelőt.
     *
     * @param commandName A parancs név.
     * @param handler     A parancs kezelője.
     */
    @Override
    public void addCommand(String commandName, CommandHandler handler) {
        commandRepository.put(commandName, handler);
    }
}
