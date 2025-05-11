import javax.swing.*;
import java.awt.*;

/**
 * A játék befejezését
 */
public class GameEndView extends JPanel implements Updatable {

    /**
     * A kártyának a neve amin a nézet van.
     */
    public static final String CARD_NAME = "gameEnd";

    /**
     * A játék végén megjelenő felirat.
     */
    private final JLabel gameEndLabel;

    /**
     * A játék végének controllerje
     */
    private final GameEndManager gameEndManager;

    /**
     * Az ablak, amiben az alkalmazás fut.
     */
    private final AppFrame appFrame;

    /**
     * Létrehozza a nézetet, a megadott függőségekkel.
     *
     * @param gameEndManager A játék végének controllerje
     * @param appFrame Az ablak, amiben az alkalmazás fut.
     */
    public GameEndView(GameEndManager gameEndManager, AppFrame appFrame) {
        this.gameEndManager = gameEndManager;
        this.appFrame = appFrame;

        ViewRepository.bind(gameEndManager, this);

        setLayout(new GridBagLayout());

        gameEndLabel = new JLabel("GAME END ERROR");
        gameEndLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gameEndLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameEndLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(gameEndLabel);
    }

    /**
     * Frissíti a nézetetet, a hozzá tartozó modell alapján.
     */
    @Override
    public void update() {
        gameEndLabel.setText(gameEndManager.showWinners());
        appFrame.switchToView(GameEndView.CARD_NAME);
    }
}
