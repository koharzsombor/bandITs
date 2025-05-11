import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class StartGameListener implements ActionListener {
    /**
     *
     */
    private final MapCreationController mapCreationController;

    /**
     *
     */
    private final AppFrame appFrame;

    /**
     *
     */
    private final JTextField gameLengthTextField;

    /**
     *
     */
    private final GameEndManager gameEndManager;

    /**
     *
     */
    private final TurnController turnController;

    /**
     * @param mapCreationController
     * @param appFrame
     * @param gameLengthTextField
     * @param gameEndManager
     * @param turnController
     */
    public StartGameListener(MapCreationController mapCreationController, AppFrame appFrame, JTextField gameLengthTextField, GameEndManager gameEndManager, TurnController turnController) {
        this.mapCreationController = mapCreationController;
        this.appFrame = appFrame;
        this.gameLengthTextField = gameLengthTextField;
        this.gameEndManager = gameEndManager;
        this.turnController = turnController;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int gameLength;
        try {
            gameLength = Integer.parseInt(gameLengthTextField.getText());
        } catch (NumberFormatException exception) {
            return;
        }
        if (gameLength < 1)
            return;

        gameEndManager.setGameLength(gameLength);
        mapCreationController.generateMap();
        appFrame.switchToView(GameView.cardName);
        turnController.beginFirstTurn();
    }
}
