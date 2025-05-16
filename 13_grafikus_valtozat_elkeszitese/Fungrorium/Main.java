import javax.swing.*;
import java.awt.*;

public class Main {
    //Controller
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
    private static ProcedualController procedualController;

    //View
    private static AppFrame appFrame;
    private static MenuView menuView;
    private static PlayerView playerView;
    private static GameStartView gameStartView;

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
        procedualController = new ProcedualControllerImpl(mapCreationController, playerContainer, tectonController);

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

    public static void main(String... args) {
        initialiseComponents();
        CardLayout cardLayout = new CardLayout();
        appFrame = new AppFrame("FungRorium", cardLayout);
        appFrame.setSize(1000, 800);
        appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Game
        GameFieldView gameFieldView = new GameFieldView();
        // Beallitani a referenciat a factoryban, hogy az uj tectonokat hozza lehessen adni a grafhoz
        SwingTectonFactory.setGameFieldView(gameFieldView);
        // TBI: Ide a tobbi factorynak szolni ugyanugy
        gameFieldView.startAnimation();

        TurnView turnView = new TurnView(turnController, gameEndManager);
        GameView gameView = new GameView(gameFieldView, turnView);

        cardLayout.addLayoutComponent(gameView, GameView.CARD_NAME);
        appFrame.add(gameView);

        //Menu
        PlayerContainerView playerContainerView = new PlayerContainerView(playerContainer);
        playerView = new PlayerView(playerContainerView, playerController);
        gameStartView = new GameStartView(appFrame, procedualController, gameEndManager, turnController, gameFieldView);
        menuView = new MenuView(playerView, gameStartView);

        cardLayout.addLayoutComponent(menuView, MenuView.CARD_NAME);
        appFrame.add(menuView);

        //GameEnd
        GameEndView gameEndView = new GameEndView(gameEndManager, appFrame);
        cardLayout.addLayoutComponent(gameEndView, GameEndView.CARD_NAME);
        appFrame.add(gameEndView);

        //Show window
        appFrame.switchToView(MenuView.CARD_NAME);
        appFrame.setVisible(true);
    }
}
