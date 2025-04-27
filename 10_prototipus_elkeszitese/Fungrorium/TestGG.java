import java.util.List;
import org.junit.jupiter.api.*;

public class TestGG {
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
    private static final String test1_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static String test1_m1 = "m1: Mycelium\n" +
            "\tgrowing boolean = true\n" +
            "\tlocation Tecton = ft2\n" +
            "\tgrowTimer int = 1\n" +
            "\tdeathTimer int = -1\n";
    private static final String test1_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static final String test1_ft2_2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
            "\tremainingEjects int = 3\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t\tmb1-speeds1\n" +
            "\t\tmb1-speeds2\n" +
            "\t\tmb1-speeds3\n" +
            "\t}\n";
    private static final String test1_m1_2 = "m1: Mycelium\n" +
            "\tgrowing boolean = false\n" +
            "\tlocation Tecton = ft2\n" +
            "\tgrowTimer int = 0\n" +
            "\tdeathTimer int = -1\n";

    @Test
    public void test1() {
        commandReader.bufferFile(test1_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();       //Troy!!
        Assertions.assertEquals(test1_ft2, output.get(0));
        Assertions.assertEquals(test1_m1, output.get(1));
        Assertions.assertEquals(test1_ft1, output.get(2));
        Assertions.assertEquals(test1_ft2_2, output.get(3));
        Assertions.assertEquals(test1_mb1, output.get(4));
        Assertions.assertEquals(test1_m1_2, output.get(5));
    }

    //Teszt2: Gombafonál sikeres gyors növesztése gombatestből FertileTectonra
    private static final String test2_Path = "Fungrorium/TestInputs/GGTests/test2.txt";
    private static final String test2_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static final String test2_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton> = {\n" +
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
        Assertions.assertEquals(test2_ft1, output.get(0));
        Assertions.assertEquals(test2_ft2, output.get(1));
        Assertions.assertEquals(test2_mb1, output.get(2));
        Assertions.assertEquals(test2_m1, output.get(3));
    }

    //Teszt3: Gombafonál sikertelen növesztése gombatestből, olyan FertileTectonra, ahol már van gombafonál
    private static final String test3_Path = "Fungrorium/TestInputs/GGTests/test3.txt";
    private static final String test3_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static final String test3_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
        Assertions.assertEquals(test3_ft1, output.get(0));
        Assertions.assertEquals(test3_ft2, output.get(1));
        Assertions.assertEquals(test3_mb1, output.get(2));
        Assertions.assertEquals(test3_m1, output.get(3));
    }

    //Teszt4: Gombafonál sikertelen növesztése gombatestből, olyan FertileTectonra , ami a növést kezdeményező gombatest tektonjával nem közvetlenül szomszédos.
    private static final String test4_Path = "Fungrorium/TestInputs/GGTests/test4.txt";
    private static final String test4_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test4_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
        Assertions.assertEquals(test4_ft1, output.get(0));
        Assertions.assertEquals(test4_ft2, output.get(1));
        Assertions.assertEquals(test4_mb1, output.get(2));
    }

    //Teszt5: Húsevő fonál általi rovarevés és gombatest növesztés
    private static final String test5_Path = "Fungrorium/TestInputs/GGTests/test5.txt";
    private static final String test5_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton> = {\n" +
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
        Assertions.assertEquals(test5_ft1, output.get(0));
        Assertions.assertEquals(test5_mb_ft1, output.get(1));
    }

    //Teszt6: Gombafonál elhalása AridTectonon
    private static final String test6_Path = "Fungrorium/TestInputs/GGTests/test6.txt";
    private static final String test6_at1 = "at1: FertileTecton\n" +
            "\tbreakTimer int = 1\n" +
            "\tneighbours List<Tecton> = {\n" +
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
        Assertions.assertEquals(test6_at1, output.getFirst());
    }

    //Teszt7: Rovarász megpróbál a rovarral műveletet (evés, vágás, mozgás) végrehajtani, amikor már nincs több művelete
    private static final String test7_Path = "Fungrorium/TestInputs/GGTests/test7.txt";
    private static final String test7_ft3 = "ft3: FertileTecton\n" +
            "\tbreakTimer int = 3\n" +
            "\tneighbours List<Tecton> = {\n" +
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
            "\tlocation Tecton = ft3 \n" +
            "\tmaxMoves int = 2\n" +
            "\tremainingMoves int = 0\n" +
            "\tsporesEaten int = 0\n" +
            "\teffectTimer int = 0\n" +
            "\tstate Insect= NORMAL\n";

    @Test
    public void test7() {
        commandReader.bufferFile(test7_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test7_ft3, output.get(0));
        Assertions.assertEquals(test7_m3, output.get(1));
        Assertions.assertEquals(test7_i1, output.get(2));
    }

    //Teszt8: Gombász megpróbál a körében olyan műveletet végezni, amire már nincs lehetősége
    private static final String test8_Path = "Fungrorium/TestInputs/GGTests/test8.txt";
    private static final String test8_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static final String test8_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static final String test8_ft3 = "ft3: FertileTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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
    private static final String test8_mlt1 = "mlt1: MultiLayeredTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
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

    @Test
    public void test8() {
        commandReader.bufferFile(test8_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test8_ft1, output.get(0));
        Assertions.assertEquals(test8_ft2, output.get(1));
        Assertions.assertEquals(test8_ft3, output.get(2));
        Assertions.assertEquals(test8_mlt1, output.get(3));
    }

    //Teszt9: Összetett teszteset, amiben rovarász és gombász és is van és a játék a valósághoz hasonlóan megy.
    private static final String test9_Path = "Fungrorium/TestInputs/GGTests/test9.txt";
    private static final String test9_ft1 = "ft1: FertileTecon\n" +
            "\tbreakTimer int = 0\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tmlt1\n" +
            "\t\tf1-1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test9_ft2 = "ft2: FertileTecon\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tmlt1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm4\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test9_ft3 = "ft3: FertileTecon\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tmlt1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n";
    private static final String test9_mlt1 = "mlt1: MultiLayeredTecton\n" +
            "\tbreakTimer int = 2\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t\tft2\n" +
            "\t\tft3\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 3\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm3\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n";
    private static final String test9_ft1_1 = "ft1-1: FertileTecon\n" +
            "\tbreakTimer int = 0\n" +
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
    private static final String test9_mb1 = "mb1: MushroomBody\n" +
            "\treamainingEjects int = 2\n" +
            "\tlocation Tecton = ft1\n" +
            "\tmushroomSpores List<Spore> = {\n" +
            "\t\tft1- speeds1\n" +
            "\t\tft1-speeds2\n" +
            "\t}\n";
    private static final String test9_i1 = "i1: Insect\n" +
            "\tlocation Tecton = mlt1\n" +
            "\tmaxMoves int = 3\n" +
            "\tremainingMoves int = 0\n" +
            "\tsporesEaten int = 1\n" +
            "\teffectTimer int = 3\n" +
            "\tstate InsectState = FAST\n";
}