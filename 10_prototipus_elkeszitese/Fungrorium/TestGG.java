import java.util.List;
import org.junit.jupiter.api.*;

public class TestBJ {
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

    //Teszt1: Gombafonál sikeres (lassú) növesztése
    private static final String test1_Path = "Fungrorium/TestInputs/GGTests/test1.txt";
    private static final String test1_ft2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 4\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +     //Kérdéses!!
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static String test1_m1 = "m1: Mycelium\n" +
            "\tgrowing boolean = true\n" +
            "\tlocation Tecton = f2 \n" +       //Kérdéses!!
            "\tgrowTimer int = 1\n" +
            "\tdeathTimer int = -1\n";

    private static final String test1_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";

    private static final String test1_ft2_2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";

    private static final String test1_mb1 = "mb1: MushroomBody\n" +
            "\treamainingEjects int = 3\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t\tmb1-speeds1\n" +
            "\t\tmb1-speeds2\n" +
            "\t}\n";

    private static final String test1_m1_2 = "m1: Mycelium\n" +
            "\tgrowing boolean = false\n" +
            "\tlocation Tecton = f2\n" +
            "\tgrowTimer int = 0\n" +
            "\tdeathTimer int = -1\n";

    @Test
    public void test1() {
        commandReader.bufferFile(test1_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();       //Troy!!
        Assertions.assertTrue(output.get(0).equals(test1_ft2));
        Assertions.assertTrue(output.get(1).equals(test1_m1));
        Assertions.assertTrue(output.get(2).equals(test1_ft1));
        Assertions.assertTrue(output.get(3).equals(test1_ft2_2));
        Assertions.assertTrue(output.get(4).equals(test1_mb1));
        Assertions.assertTrue(output.get(5).equals(test1_m1_2));
    }

    //Teszt2: Gombafonál sikeres gyors növesztése gombatestből FertileTectonra
    private static final String test2_Path = "Fungrorium/TestInputs/GGTests/test2.txt";
    private static final String test2_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 4\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";

    private static final String test2_ft2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 4\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t\tspeeds1\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";

    private static final String test2_mb1 = "mb1: MushroomBody\n" +
            "\treamainingEjects int = 3\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t\tmb1-speeds1\n" +
            "\t}\n";

    private static final String test2_m1 = "m1: Mycelium\n" +
            "\tgrowing boolean = false\n" +
            "\tlocation Tecton = f2\n" +
            "\tgrowTimer int = 0\n" +
            "\tdeathTimer int = 0\n";

    @Test
    public void test2() {
        commandReader.bufferFile(test2_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test2_ft1));
        Assertions.assertTrue(output.get(1).equals(test2_ft2));
        Assertions.assertTrue(output.get(2).equals(test2_mb1));
        Assertions.assertTrue(output.get(3).equals(test2_m1));
    }

    //Teszt3: Gombafonál sikertelen növesztése gombatestből, olyan FertileTectonra, ahol már van gombafonál
    private static final String test3_Path = "Fungrorium/TestInputs/GGTests/test3.txt";
    private static final String test3_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";

    private static final String test3_ft2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";

    private static final String test3_mb1 = "mb1: MushroomBody\n" +
            "\treamainingEjects int = 3\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t\tmb1-speeds1\n" +
            "\t\tmb1-speeds2\n" +
            "\t}\n";

    private static final String test3_m1 = "m1: Mycelium\n" +
            "\tgrowing boolean = false\n" +
            "\tlocation Tecton = f2\n" +
            "\tgrowTimer int = 0\n" +
            "\tdeathTimer int = -1\n";

    @Test
    public void test3() {
        commandReader.bufferFile(test3_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test3_ft1));
        Assertions.assertTrue(output.get(1).equals(test3_ft2));
        Assertions.assertTrue(output.get(2).equals(test3_mb1));
        Assertions.assertTrue(output.get(3).equals(test3_m1));
    }

    //Teszt4: Gombafonál sikertelen növesztése gombatestből, olyan FertileTectonra , ami a növést kezdeményező gombatest tektonjával nem közvetlenül szomszédos.
    private static final String test4_Path = "Fungrorium/TestInputs/GGTests/test4.txt";
    private static final String test4_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test4_ft2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test4_mb1 = "mb1: MushroomBody\n" +
            "\treamainingEjects int = 3\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t\tmb1-speeds1\n" +
            "\t\tmb1-speeds2\n" +
            "\t}\n";

    @Test
    public void test4() {
        commandReader.bufferFile(test4_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test4_ft1));
        Assertions.assertTrue(output.get(1).equals(test4_ft2));
        Assertions.assertTrue(output.get(2).equals(test4_mb1));
    }

    //Teszt5: Húsevő fonál általi rovarevés és gombatest növesztés
    private static final String test5_Path = "Fungrorium/TestInputs/GGTests/test5.txt";
    private static final String test5_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 4\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb-ft1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tcm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test5_mb_ft1 = "mb-ft1: MushroomBody\n" +
            "\treamainingEjects int = 3\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t}\n";

    @Test
    public void test5() {
        commandReader.bufferFile(test5_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test5_ft1));
        Assertions.assertTrue(output.get(1).equals(test5_mb_ft1));
    }

    //Teszt6: Gombafonál elhalása AridTectonon
    private static final String test6_Path = "Fungrorium/TestInputs/GGTests/test6.txt";
    private static final String test6_at1 = "at1: FertileTecon\n" +
            "\tbreakTimer int = 1\n" +
            "\tneighbours List<Tecton>  = {\n" +
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
    public void test6() {
        commandReader.bufferFile(test6_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test6_at1));
    }

    //Teszt7: Rovarász megpróbál a rovarral műveletet (evés, vágás, mozgás) végrehajtani, amikor már nincs több művelete
    private static final String test7_Path = "Fungrorium/TestInputs/GGTests/test7.txt";
    private static final String test7_ft3 = "ft3: FertileTecon\n" +
            "\tbreakTimer int = 4\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tf2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t\tspeeds1\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm3\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n";
    private static final String test7_m3 = "m3: Mycelium\n" +
            "\tgrowing boolean = false\n" +
            "\tlocation Tecton = f3\n" +
            "\tgrowTimer int = 0\n" +
            "\tdeathTimer int = -1\n";
    private static final String test7_i1 = "i1: Insect\n" +
            "\tlocation = ft3\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n";

    @Test
    public void test7() {
        commandReader.bufferFile(test7_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test7_ft3));
        Assertions.assertTrue(output.get(1).equals(test7_m3));
        Assertions.assertTrue(output.get(2).equals(test7_i1));
    }

    //Teszt8: Gombász megpróbál a körében olyan műveletet végezni, amire már nincs lehetősége
    private static final String test8_Path = "Fungrorium/TestInputs/GGTests/test8.txt";
    private static final String test8_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tf3\n" +
            "\t\tf4\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test8_ft2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tf3\n" +
            "\t\tf4\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb2\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test8_ft3 = "ft3: FertileTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tf1\n" +
            "\t\tf2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t\tspeeds1\n" +
            "\t\tspeeds5\n" +
            "\t\tspeeds6\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb3\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm2\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test8_mlt1 = "mlt1: MultiLayeredTecon\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton>  = {\n" +
            "\t\tf1\n" +
            "\t\tf2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 3\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t\tspeeds2\n" +
            "\t\tspeeds3\n" +
            "\t\tspeeds4\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb4\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
}