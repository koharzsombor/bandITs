import java.util.*;

/**
 * A parancsokat olvasó objektum implementációja.
 */
public class CommandReaderImpl implements CommandReader {

    /**
     * A parancsokat továbbküldő objektum.
     */
    private final CommandRouter commandRouter;

    /**
     * Egy input puffer, amiből az olvasó elsődlegesen kiolvas.
     */
    private final Queue<String> inputBuffer = new LinkedList<>();

    /**
     * @param commandRouter
     */
    public CommandReaderImpl(CommandRouter commandRouter) {
        this.commandRouter = commandRouter;
    }

    /**
     * Értelmezi a következő parancsot a pufferből, ha a puffer üres, akkor a játékostól kér új
     * parancsot.
     */
    @Override
    public void readNextCommand() {
        if (inputBuffer.isEmpty()) {
            try(Scanner inputScanner = new Scanner(System.in)) {
                String nextLine = inputScanner.nextLine();
                String[] splitLine = nextLine.split("\\s+");
                String[] params = Arrays.copyOfRange(splitLine, 1, splitLine.length);

                InputCommand command = new InputCommand(splitLine[0], params);
            }
        }
        else {
            String nextLine = inputBuffer.poll();
            String[] splitLine = nextLine.split("\\s+");
            String[] params = Arrays.copyOfRange(splitLine, 1, splitLine.length);

            InputCommand command = new InputCommand(splitLine[0], params);
        }
    }

    /**
     * Egy parancsot a pufferbe helyez.
     *
     * @param input A pufferbe helyezendő parancs.
     */
    @Override
    public void bufferCommand(String input) {
        inputBuffer.offer(input);
    }
}
