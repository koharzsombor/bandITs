import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestRG {

    private CommandReader commandReader;
    private CommandRouter commandRouter;
    private PlayerContainer playerContainer;
    private RoundObserver roundObserver;
    private TurnController turnController;
    private TectonController tectonController;
    private InsectController insectController;
    private PlayerController playerController;
    private GameEndManager gameEndManager;
    private TraceablePrinter traceablePrinter;
    private MushroomBodyController mushroomBodyController;
    private MapCreationController mapCreationController;
    private GrowthController growthController;

    @BeforeEach
    public void initialiseComponents() {
        ObjectRegistry.clearRegistry();

        roundObserver = new RoundObserverImpl();
        playerContainer = new PlayerContainerImpl(roundObserver);

        turnController = new TurnControllerImpl(playerContainer);
        tectonController = new TectonControllerImpl();
        insectController = new InsectControllerImpl();
        playerController = new PlayerControllerImpl(playerContainer);
        mushroomBodyController = new MushroomBodyControllerImpl();
        mapCreationController = new MapCreationControllerImpl(roundObserver);
        growthController = new GrowthControllerImpl();

        commandRouter = new CommandRouterImpl(turnController);
        commandReader = new CommandReaderImpl(commandRouter);
        traceablePrinter = new TrablePrinterImpl();
        gameEndManager = new GameEndManagerImpl(traceablePrinter, playerContainer);

        commandRouter.addCommand("RUN", commandReader);
        commandRouter.addCommand("STATE", traceablePrinter);
        commandRouter.addCommand("ADD_PLAYER", playerController);
        commandRouter.addCommand("CREATE_TECTON", mapCreationController);
        commandRouter.addCommand("CREATE_MYCELIUM", mapCreationController);
        commandRouter.addCommand("CREATE_MUSHROOMBODY", mapCreationController);
        commandRouter.addCommand("CREATE_INSECT", mapCreationController);
        commandRouter.addCommand("ENDTURN", turnController);
        commandRouter.addCommand("START_GAME", turnController);
        commandRouter.addCommand("SET_ENDGAMETIMER", gameEndManager);
        commandRouter.addCommand("END_GAME", gameEndManager);
        commandRouter.addCommand("GROW_MYCELIUM", growthController);
        commandRouter.addCommand("GROW_MUSHROOMBODY", growthController);
        commandRouter.addCommand("SET_BREAKTIMER", tectonController);
        commandRouter.addCommand("ADD_NEIGHBOUR", tectonController);
        commandRouter.addCommand("ADD_MYCELIUM_TO_TECTON", tectonController);
        commandRouter.addCommand("PUT_SPORE", tectonController);
        commandRouter.addCommand("EJECT_SPORES", mushroomBodyController);
        commandRouter.addCommand("DEACTIVATE", mushroomBodyController);
        commandRouter.addCommand("SET_REMAININGEJECTS", mushroomBodyController);
        commandRouter.addCommand("ADD_SPORE", mushroomBodyController);
        commandRouter.addCommand("CUT", insectController);
        commandRouter.addCommand("EAT", insectController);
        commandRouter.addCommand("MOVE", insectController);

    }

    //Teszt1: Uj Tecton sikeres legyartasa
    private static final String test1_Path = "TestInputs/RGTests/test1.txt";
    private static final String test1_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft2\n" +
            "\t\tft3\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test1_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t\tft3\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test1_ft3 = "ft3: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    @Test
    public void test1() {
        commandReader.bufferFile(test1_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test1_ft1, output.get(0));
        Assertions.assertEquals(test1_ft2, output.get(1));
        Assertions.assertEquals(test1_ft3, output.get(2));
    }

    //Teszt2: Tectontores
    private static final String test2_Path = "TestInputs/RGTests/test2.txt";
    private static final String test2_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1-1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n";
    private static final String test2_ft1_1 = "ft1-1: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    @Test
    public void test2() {
        commandReader.bufferFile(test2_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test2_ft1, output.get(0));
        Assertions.assertEquals(test2_ft1_1, output.get(1));
    }

    //Teszt3: Jatek vegenek kezelese
    private static final String test3_Path = "TestInputs/RGTests/test3.txt";
    @Test
    public void test3() {
        roundObserver.subscribe(gameEndManager);

        commandReader.bufferFile(test3_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals("WINNERS:", output.get(0));
        Assertions.assertEquals("MYCOLOGIST:", output.get(1));
        Assertions.assertEquals("mycologist1", output.get(2));
        Assertions.assertEquals("ENTOMOLOGIST:", output.get(3));
        Assertions.assertEquals("entomologist1", output.get(4));
    }
}
