import java.util.List;
import org.junit.jupiter.api.*;

public class Tests_TSz {
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

    //Teszt1: Gombatest sikeres növesztése FertileTectonra (nem SustainingTecton, nem MultiLayeredTecton
            // és nem AridTecton) gombafonál által
    private static final String test1_Path = "Fungrorium/TestInputs/TSZTests/test1.txt";

    private static final String test1_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t\tm1
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test1_m1 = """
    m1: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft1
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test1_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 3
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tmb1-speeds1
    \t}
    """;

    @Test
    void test1() {
        commandReader.bufferFile(test1_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test1_ft1,output.get(0));
        Assertions.assertEquals(test1_m1,output.get(1));
        Assertions.assertEquals(test1_mb1,output.get(2));
    }


    //Teszt2: Gombatest spórahiány miatti sikertelen növesztése FertileTectonra (nem SustainingTecton,
            // nem MultiLayeredTecton és nem AridTecton) gombafonál által
    private static final String test2_Path = "Fungrorium/TestInputs/TSZTests/test2.txt";

    private static final String test2_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm1
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test2_m1 = """
    m1: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft1
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    @Test
    void test2() {
        commandReader.bufferFile(test2_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test2_ft1,output.get(0));
        Assertions.assertEquals(test2_m1,output.get(1));
    }


    //Teszt3: Gombatest sikertelen növesztése gombafonál által olyan FertileTectonra (nem SustainingTecton,
            // nem MultiLayeredTecton és nem AridTecton), amelyen már van gombatest
    private static final String test3_Path = "Fungrorium/TestInputs/TSZTests/test3.txt";

    private static final String test3_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t\tm1
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test3_m1 = """
    m1: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft1
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test3_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 3
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tmb1-speeds1
    \t\tmb1-speeds2
    \t}
    """;

    @Test
    void test3() {
        commandReader.bufferFile(test3_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test3_ft1,output.get(0));
        Assertions.assertEquals(test3_m1,output.get(1));
        Assertions.assertEquals(test3_mb1,output.get(2));
    }

    //Teszt4: Gombatest sikertelen növesztése gombafonál által SemiFertileTectonra
    private static final String test4_Path = "Fungrorium/TestInputs/TSZTests/test4.txt";

    private static final String test4_sft1 = """
    sft1: SemiFertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm1
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test4_m1 = """
    m1: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = sft1
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    @Test
    void test4() {
        commandReader.bufferFile(test4_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test4_sft1,output.get(0));
        Assertions.assertEquals(test4_m1,output.get(1));
    }


    //Teszt5: Gombatest sikeres spórakilövése a gombatest elhelyezkedése szerinti tektonnal szomszédos FertileTectonra
            // (nem SustainingTecton, nem MultiLayeredTecton és nem AridTecton)
    private static final String test5_Path = "Fungrorium/TestInputs/TSZTests/test5.txt";

    private static final String test5_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test5_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t\tmb1-speeds1
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test5_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 2
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tmb1-speeds2
    \t}
    """;

    @Test
    void test5() {
        commandReader.bufferFile(test5_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test5_ft1,output.get(0));
        Assertions.assertEquals(test5_ft2,output.get(1));
        Assertions.assertEquals(test5_mb1,output.get(2));
    }


    //Teszt6: Gombatest sikeres spórakilövése olyan FertileTectonra (nem SustainingTecton, nem MultiLayeredTecton
            // és nem AridTecton), amely a gombatest elhelyezkedése szerinti tektonnal szomszédos tekton szomszédja
    private static final String test6_Path = "Fungrorium/TestInputs/TSZTests/test6.txt";

    private static final String test6_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test6_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test6_ft3 = """
    ft3: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t\tmb1-speeds1
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test6_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 0
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t}
    """;

    @Test
    void test6() {
        commandReader.bufferFile(test6_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test6_ft1,output.get(0));
        Assertions.assertEquals(test6_ft2,output.get(1));
        Assertions.assertEquals(test6_ft3,output.get(2));
        Assertions.assertEquals(test6_mb1,output.get(3));
    }

    //Teszt7: Gombatest spórahiány miatti sikertelen spórakilövése a gombatest elhelyezkedése szerinti tektonnal
            // szomszédos FertileTectonra (nem SustainingTecton, nem MultiLayeredTecton és nem AridTecton)
    private static final String test7_Path = "Fungrorium/TestInputs/TSZTests/test7.txt";

    private static final String test7_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test7_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tmb1-speeds1
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test7_ft3 = """
    ft3: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;
        
    private static final String test7_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 2
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tmb1-speeds2
    \t}
    """;

    @Test
    void test7() {
        commandReader.bufferFile(test7_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test7_ft1,output.get(0));
        Assertions.assertEquals(test7_ft2,output.get(1));
        Assertions.assertEquals(test7_ft3,output.get(2));
        Assertions.assertEquals(test7_mb1,output.get(3));
    }


    //Teszt8: Gombatest sikertelen spórakilövése olyan FertileTectonra (nem SustainingTecton, nem MultiLayeredTecton
            // és nem AridTecton), amely a gombatest elhelyezkedése szerinti tektonnal szomszédos tekton szomszédja
    private static final String test8_Path = "Fungrorium/TestInputs/TSZTests/test8.txt";

    private static final String test8_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test8_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test8_ft3 = """
    ft3: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test8_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 3
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t\tmb1-speeds1
    \t\tmb1-speeds2
    \t}
    """;

    @Test
    void test8() {
        commandReader.bufferFile(test8_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test8_ft1, output.get(0));
        Assertions.assertEquals(test8_ft2, output.get(1));
        Assertions.assertEquals(test8_ft3, output.get(2));
        Assertions.assertEquals(test8_mb1, output.get(3));
    }


    //Teszt9: Gombatest sikertelen spórakilövése olyan FertileTectonra (nem SustainingTecton, nem MultiLayeredTecton
            // és nem AridTecton), amely a gombatest elhelyezkedése szerinti tekton harmadik szomszédja
    private static final String test9_Path = "Fungrorium/TestInputs/TSZTests/test9.txt";

    private static final String test9_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test9_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test9_ft3 = """
    ft3: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t\tft4
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test9_ft4 = """
    ft4: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test9_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 1
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tspeeds1
    \t\tspeeds2
    \t\tspeeds3
    \t\tmb1-speeds1
    \t\tmb1-speeds2
    \t}
    """;

    @Test
    void test9() {
        commandReader.bufferFile(test9_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test9_ft1, output.get(0));
        Assertions.assertEquals(test9_ft2, output.get(1));
        Assertions.assertEquals(test9_ft3, output.get(2));
        Assertions.assertEquals(test9_ft4, output.get(3));
        Assertions.assertEquals(test9_mb1, output.get(4));
    }


    //Teszt10: Inaktív gombatest sikertelen spórakilövése a gombatest elhelyezkedése szerinti tektonnal szomszédos
            // FertileTectonra (nem SustainingTecton, nem MultiLayeredTecton és nem AridTecton)
    private static final String test10_Path = "Fungrorium/TestInputs/TSZTests/test10.txt";

    private static final String test10_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test10_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 3
    \tneighbours List<Tecton> = {
    \t\tft1
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test10_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 0
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t}
    """;

    @Test
    void test10() {
        commandReader.bufferFile(test10_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test10_ft1, output.get(0));
        Assertions.assertEquals(test10_ft2, output.get(1));
        Assertions.assertEquals(test10_mb1, output.get(2));
    }


    //Teszt11: StunSpore sikeres elhelyezése FertileTectonon (nem SustainingTecton, nem MultiLayeredTecton és nem AridTecton)
    private static final String test11_Path = "Fungrorium/TestInputs/TSZTests/test11.txt";

    private static final String test11_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 5
    \tneighbours List<Tecton> = {
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tstuns1
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    @Test
    void test11() {
        commandReader.bufferFile(test11_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test11_ft1, output.get(0));
    }


    //Teszt12: Gombatest inaktívvá válása a harmadik spórakilövését követően
    private static final String test12_Path = "Fungrorium/TestInputs/TSZTests/test12.txt";

    private static final String test12_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 2
    \tneighbours List<Tecton> = {
    \t\tft2
    \t\tft3
    \t\tft4
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t\tm1
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test12_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 2
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tmb1-speeds1
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm2
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test12_ft3 = """
    ft3: FertileTecton
    \tbreakTimer int = 2
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft2
    \t\tft4
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tmb1-speeds2
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm3
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test12_ft4 = """
    ft4: FertileTecton
    \tbreakTimer int = 2
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tmb1-speeds3
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm4
    \t}
    \toccupants List<Insect> = {
    \t\ti1
    \t}
    """;

    private static final String test12_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 0
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t}
    """;

    private static final String test12_m1 = """
    m1: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft1
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test12_m2 =  """
    m2: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft2
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test12_m3 = """
    m3: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft3
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test12_m4 = """
    m4: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft4
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test12_i1 = """
    i1: Insect
    \tlocation Tecton = ft4
    \tmaxMoves int = 2
    \tremainingMoves int = 1
    \tsporesEaten int = 0
    \teffectTimer int = 0
    \tstate InsectState = NORMAL
    """;

    @Test
    void test12() {
        commandReader.bufferFile(test12_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test12_ft1, output.get(0));
        Assertions.assertEquals(test12_ft2, output.get(1));
        Assertions.assertEquals(test12_ft3, output.get(2));
        Assertions.assertEquals(test12_ft4, output.get(3));
        Assertions.assertEquals(test12_mb1, output.get(4));
        Assertions.assertEquals(test12_m1, output.get(5));
        Assertions.assertEquals(test12_m2, output.get(6));
        Assertions.assertEquals(test12_m3, output.get(7));
        Assertions.assertEquals(test12_m4, output.get(8));
        Assertions.assertEquals(test12_i1, output.get(9));
    }


    //Teszt13: Rovar által elvágott gombafonál elsorvadása és az elfogyasztott spóra rovarra gyakorolt hatása
    private static final String test13_Path = "Fungrorium/TestInputs/TSZTests/test13.txt";

    private static final String test13_ft1 = """
    ft1: FertileTecton
    \tbreakTimer int = 1
    \tneighbours List<Tecton> = {
    \t\tft2
    \t\tft3
    \t\tft5
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = mb1
    \tmycelia Queue<Mycelium> = {
    \t\tm1
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test13_ft2 = """
    ft2: FertileTecton
    \tbreakTimer int = 1
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft3
    \t\tft5
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t\tmb1-speeds1
    \t\tmb1-speeds2
    \t\tmb1-speeds3
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm2
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test13_ft3 = """
    ft3: FertileTecton
    \tbreakTimer int = 1
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft2
    \t\tft4
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test13_ft4 = """
    ft4: FertileTecton
    \tbreakTimer int = 1
    \tneighbours List<Tecton> = {
    \t\tft3
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t}
    \toccupants List<Insect> = {
    \t}
    """;

    private static final String test13_ft5 = """
    ft5: FertileTecton
    \tbreakTimer int = 1
    \tneighbours List<Tecton> = {
    \t\tft1
    \t\tft2
    \t}
    \tmyceliumCapacity int = 1
    \tspores Queue<Spore> = {
    \t}
    \tmushroomBody MushroomBody = null
    \tmycelia Queue<Mycelium> = {
    \t\tm4
    \t}
    \toccupants List<Insect> = {
    \t\ti1
    \t}
    """;

    private static final String test13_mb1 = """
    mb1: MushroomBody
    \tremainingEjects int = 2
    \tlocation Tecton = ft1
    \tmushroomSpores List<Spore> = {
    \t\tmb1-speeds4
    \t\tmb1-speeds5
    \t}
    """;

    private static final String test13_m1 = """
    m1: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft1
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test13_m2 = """
    m2: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft2
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test13_m4 = """
    m4: Mycelium
    \tgrowing boolean = false
    \tlocation Tecton = ft5
    \tgrowTimer int = 0
    \tdeathTimer int = -1
    """;

    private static final String test13_i1 = """
    i1: Insect
    \tlocation Tecton = ft5
    \tmaxMoves int = 2
    \tremainingMoves int = 2
    \tsporesEaten int = 1
    \teffectTimer int = 0
    \tstate InsectState = NORMAL
    """;

    @Test
    void test13() {
        commandReader.bufferFile(test13_Path);
        commandReader.readAllBufferedCommands();

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertEquals(test13_ft1, output.get(0));
        Assertions.assertEquals(test13_ft2, output.get(1));
        Assertions.assertEquals(test13_ft3, output.get(2));
        Assertions.assertEquals(test13_ft4, output.get(3));
        Assertions.assertEquals(test13_ft5, output.get(4));
        Assertions.assertEquals(test13_mb1, output.get(5));
        Assertions.assertEquals(test13_m1, output.get(6));
        Assertions.assertEquals(test13_m2, output.get(7));
        Assertions.assertEquals(test13_m4, output.get(8));
        Assertions.assertEquals(test13_i1, output.get(9));
    }
}