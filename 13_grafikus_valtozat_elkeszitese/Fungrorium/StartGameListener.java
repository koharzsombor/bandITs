import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 */
public class StartGameListener implements ActionListener {
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
     *
     */
    private final ProcedualController procedualController;

    private final GameFieldView gameFieldView;

    /**
     * @param appFrame
     * @param gameLengthTextField
     * @param gameEndManager
     * @param turnController
     */
    public StartGameListener(ProcedualController procedualController, AppFrame appFrame,
                             JTextField gameLengthTextField, GameEndManager gameEndManager, TurnController turnController, GameFieldView gameFieldView) {
        this.appFrame = appFrame;
        this.gameLengthTextField = gameLengthTextField;
        this.gameEndManager = gameEndManager;
        this.turnController = turnController;
        this.procedualController = procedualController;
        this.gameFieldView = gameFieldView;
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
        gameFieldView.SetTectons(new ArrayList<>(procedualController.generateMap()));
        appFrame.switchToView(GameView.CARD_NAME);
        turnController.beginFirstTurn();
    }
}
