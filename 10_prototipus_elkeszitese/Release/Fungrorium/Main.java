public class Main {
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

        ObjectRegistry.registerObject("TURN", turnController);
        ObjectRegistry.registerObject("GAME_END", gameEndManager);

        roundObserver.subscribe(gameEndManager);
    }

    private static String title = """
    ====================================
        ╭━━━╮        ╭━━━╮
        ┃╭━━╯        ┃╭━╮┃
        ┃╰━━┳╮╭┳━╮╭━━┫╰━╯┣━━┳━┳┳╮╭┳╮╭╮
        ┃╭━━┫┃┃┃╭╮┫╭╮┃╭╮╭┫╭╮┃╭╋┫┃┃┃╰╯┃
        ┃┃  ┃╰╯┃┃┃┃╰╯┃┃┃╰┫╰╯┃┃┃┃╰╯┃┃┃┃
        ╰╯  ╰━━┻╯╰┻━╮┣╯╰━┻━━┻╯╰┻━━┻┻┻╯
                  ╭━╯┃
                  ╰━━╯
    ====================================
    """;

    public static void main(String... args) {
        initialiseComponents();
        System.out.println(title);
        System.out.println("A parancsok listája és magyarázatuk eléréséhez add ki a ? parancsot!");

        while (true) {
            commandReader.readNextCommand();
        }
    }
}
