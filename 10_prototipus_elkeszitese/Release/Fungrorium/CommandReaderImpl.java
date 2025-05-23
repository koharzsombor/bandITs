import java.io.File;
import java.io.FileNotFoundException;
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
     * A létrehozáshoz, szükség egy objektum aminek továbbküldheti a parancsot.
     * @param commandRouter A parancsokat továbbküldő objektum.
     */
    public CommandReaderImpl(CommandRouter commandRouter) {
        this.commandRouter = commandRouter;
    }

    private final Scanner inputScanner = new Scanner(System.in);

    /**
     * Értelmezi a következő parancsot a pufferből, ha a puffer üres, akkor a játékostól kér új
     * parancsot.
     */
    @Override
    public void readNextCommand() {
        String nextLine;
        if (inputBuffer.isEmpty()) {
            System.out.print(">> ");
            nextLine = inputScanner.nextLine();
        }
        else {
            nextLine = inputBuffer.poll();
        }
        String[] splitLine = nextLine.split("\\s+");
        String[] params = Arrays.copyOfRange(splitLine, 1, splitLine.length);

        InputCommand command = new InputCommand(splitLine[0], params);
        commandRouter.routeCommand(command);
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

    /**
     * Egy fájlban lévő összes sort külön parancsként a pufferbe helyez.
     *
     * @param path A fájl elérési útja.
     */
    @Override
    public void bufferFile(String path) {
        File filePath = new File(path);
        try (Scanner scanner = new Scanner(filePath)) {
            while (scanner.hasNextLine()) {
                inputBuffer.offer(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kiolvassa az összes pufferban lévő parancsot.
     */
    @Override
    public void readAllBufferedCommands() {
        while (!inputBuffer.isEmpty()) {
            readNextCommand();
        }
    }
}
