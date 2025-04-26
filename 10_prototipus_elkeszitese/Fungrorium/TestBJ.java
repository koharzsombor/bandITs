import java.util.List;

public class TestBJ {
    private static CommandReader commandReader;
    private static CommandRouter commandRouter;
    private static PlayerContainer playerContainer;
    private static RoundObserver roundObserver;
    private static TurnController turnController;
    private static TectonController tectonController;
    private static InsectController insectController;
    private static PlayerController playerController;
    private static GameEndManager gameEndManager;
    private static TraceablePrinter traceablePrinter;
    private static MushroomBodyController mushroomBodyController;
    private static MapCreationController mapCreationController;
    private static GrowthController growthController;

    @BeforeEach
    private static void initialiseComponents() {
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
        commandRouter.addCommand("END_TURN", turnController);
        commandRouter.addCommand("START_GAME", turnController);
        commandRouter.addCommand("SET_ENDGAMETIMER", gameEndManager);
        commandRouter.addCommand("END_GAME", gameEndManager);
        commandRouter.addCommand("GROW_MYCELIUM", growthController);
        commandRouter.addCommand("GROW_MUSHROOMBODY", growthController);
        commandRouter.addCommand("SET_BREAKTIMER", tectonController);
        commandRouter.addCommand("ADD_NEIGHBOUR", tectonController);
        commandRouter.addCommand("ADD_MYCELIUM", tectonController);
        commandRouter.addCommand("PUT_SPORE", tectonController);
        commandRouter.addCommand("EJECT_SPORES", mushroomBodyController);
        commandRouter.addCommand("DEACTIVATE", mushroomBodyController);
        commandRouter.addCommand("SET_REMAININGEJECTS", mushroomBodyController);
        commandRouter.addCommand("ADD_SPORE", mushroomBodyController);
        commandRouter.addCommand("CUT", insectController);
        commandRouter.addCommand("EAT", insectController);
        commandRouter.addCommand("MOVE", insectController);
    }

    //Teszt1: Rovar létrehozása és letevése
    private static final String test1_Path = "Fungrorium/TestInputs/BJTests/test1.txt";
    private static final String test1_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test1_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test1_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test1() {
        commandReader.bufferFile(test1_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test1_ft1));
        Assertions.assertTrue(output.get(1).equals(test1_m1));
        Assertions.assertTrue(output.get(2).equals(test1_i1));
    }

    //Teszt2: Rovar mozgatása
    private static final String test2_Path = "Fungrorium/TestInputs/BJTests/test2.txt";
    private static final String test2_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n\n";
    private static final String test2_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm2\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test2_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test2_m2 = "m2: Mycelium\n" +
            "\tlocation = ft2\n\n";
    private static final String test2_i1 = "i1: Insect\n" +
            "\tlocation = ft2\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 1\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test2() {
        commandReader.bufferFile(test2_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test2_ft1));
        Assertions.assertTrue(output.get(1).equals(test2_ft2));
        Assertions.assertTrue(output.get(2).equals(test2_m1));
        Assertions.assertTrue(output.get(3).equals(test2_m2));
        Assertions.assertTrue(output.get(4).equals(test2_i1));
    }

    //Teszt3: Rovar sikertelen mozgatása nem-szomszédos tektonra
    private static final String test3_Path = "Fungrorium/TestInputs/BJTests/test3.txt";
    private static final String test3_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n\n";
    private static final String test3_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm2\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test3_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test3_m2 = "m2: Mycelium\n" +
            "\tlocation = ft2\n\n";
    private static final String test3_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test3() {
        commandReader.bufferFile(test3_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test3_ft1));
        Assertions.assertTrue(output.get(1).equals(test3_ft2));
        Assertions.assertTrue(output.get(2).equals(test3_m1));
        Assertions.assertTrue(output.get(3).equals(test3_m2));
        Assertions.assertTrue(output.get(4).equals(test3_i1));
    }

    //Teszt4: Rovar sikertelen mozgatása olyan tektonra, amelyen nincs gombafonál
    private static final String test4_Path = "Fungrorium/TestInputs/BJTests/test4.txt";
    private static final String test4_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test4_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
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
            "\t}\n\n";
    private static final String test4_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test4_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test4() {
        commandReader.bufferFile(test4_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test4_ft1));
        Assertions.assertTrue(output.get(1).equals(test4_ft2));
        Assertions.assertTrue(output.get(2).equals(test4_m1));
        Assertions.assertTrue(output.get(3).equals(test4_i1));
    }

    //Teszt5: Rovar általi spóraevés következtében kettészakadás
    private static final String test5_Path = "Fungrorium/TestInputs/BJTests/test5.txt";
    private static final String test5_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t\ti1-1\n" +
            "\t}\n\n";
    private static final String test5_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test5_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    private static final String test5_i1_1 = "i1-1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test5() {
        commandReader.bufferFile(test5_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test5_ft1));
        Assertions.assertTrue(output.get(1).equals(test5_m1));
        Assertions.assertTrue(output.get(2).equals(test5_i1));
        Assertions.assertTrue(output.get(3).equals(test5_i1_1));
    }

    //Teszt6: Rovar általi spóraevés következtében Slow állapotba kerülés
    private static final String test6_Path = "Fungrorium/TestInputs/BJTests/test6.txt";
    private static final String test6_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test6_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test6_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 1\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = SLOW\n\n";
    @Test
    void test6() {
        commandReader.bufferFile(test6_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test6_ft1));
        Assertions.assertTrue(output.get(1).equals(test6_m1));
        Assertions.assertTrue(output.get(2).equals(test6_i1));
    }

    //Teszt7: Rovar általi spóraevés következtében Fast állapotba kerülés
    private static final String test7_Path = "Fungrorium/TestInputs/BJTests/test7.txt";
    private static final String test7_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test7_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test7_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 3\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = FAST\n\n";
    @Test
    void test7() {
        commandReader.bufferFile(test7_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test7_ft1));
        Assertions.assertTrue(output.get(1).equals(test7_m1));
        Assertions.assertTrue(output.get(2).equals(test7_i1));
    }

    //Teszt8: Rovar általi spóraevés következtében PreventCut állapotba kerülés
    private static final String test8_Path = "Fungrorium/TestInputs/BJTests/test8.txt";
    private static final String test8_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test8_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test8_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = CANNOT_CUT\n\n";
    @Test
    void test8() {
        commandReader.bufferFile(test8_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test8_ft1));
        Assertions.assertTrue(output.get(1).equals(test8_m1));
        Assertions.assertTrue(output.get(2).equals(test8_i1));
    }

    //Teszt9: Rovar általi spóraevés következtében Stunned állapotba kerülés
    private static final String test9_Path = "Fungrorium/TestInputs/BJTests/test9.txt";
    private static final String test9_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test9_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test9_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 0\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = STUNNED\n\n";
    @Test
    void test9() {
        commandReader.bufferFile(test9_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test9_ft1));
        Assertions.assertTrue(output.get(1).equals(test9_m1));
        Assertions.assertTrue(output.get(2).equals(test9_i1));
    }

    //Teszt10: Rovar általi sikertelen spóraevés
    private static final String test10_Path = "Fungrorium/TestInputs/BJTests/test10.txt";
    private static final String test10_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test10_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test10_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test10() {
        commandReader.bufferFile(test10_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test10_ft1));
        Assertions.assertTrue(output.get(1).equals(test10_m1));
        Assertions.assertTrue(output.get(2).equals(test10_i1));
    }

    //Teszt11: Rovar általi gombafonál elvágás
    private static final String test11_Path = "Fungrorium/TestInputs/BJTests/test11.txt";
    private static final String test11_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
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
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test11_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test11_mb1 = "mb1: MushroomBody\n" +
            "\tlocation = ft2\n" +
            "\tremainingEjects = 3\n\n";
    private static final String test11_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n" +
            "\tdeathTimer = 2\n\n";
    private static final String test11_i1 = "i1: Insect\n" +
            "\tlocation = ft2\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    @Test
    void test11() {
        commandReader.bufferFile(test11_Path);

        List<String> output = traceablePrinter.readHistroy();
        Assertions.assertTrue(output.get(0).equals(test11_ft1));
        Assertions.assertTrue(output.get(1).equals(test11_ft2));
        Assertions.assertTrue(output.get(2).equals(test11_mb1));
        Assertions.assertTrue(output.get(3).equals(test11_m1));
        Assertions.assertTrue(output.get(4).equals(test11_i1));
    }
}
