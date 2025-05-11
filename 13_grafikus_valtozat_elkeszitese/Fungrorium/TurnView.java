import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A kör álloptának megjelenítését vezérlő nézet.
 */
public class TurnView extends JPanel implements Updatable {
    /**
     * A köröket irányító controller.
     */
    private final TurnController turnController;

    /**
     * A játék végégig visszaszámláló objektum.
     */
    private final GameEndManager gameEndManager;

    /**
     * A kör állapotát kijelző felirat.
     */
    private final JLabel turnLabel;

    /**
     * Létrehozza a nézetet a megfelelő függőségekkel.
     *
     * @param turnController A köröket irányító controller.
     * @param gameEndManager A játék végégig visszaszámláló objektum.
     */
    public TurnView(TurnController turnController, GameEndManager gameEndManager) {
        this.gameEndManager = gameEndManager;
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        this.turnController = turnController;
        setLayout(new BorderLayout());

        JButton turnEndButton = new JButton("End Turn");
        turnEndButton.addActionListener(new TurnEndButtonListener(turnController));

        turnLabel = new JLabel("TURN DISPLAY ERROR");

        add(turnLabel, BorderLayout.WEST);
        add(turnEndButton, BorderLayout.EAST);
        ViewRepository.bind(turnController, this);
    }

    /**
     * Frissíti a nézetetet, a hozzá tartozó modell alapján.
     */
    @Override
    public void update() {
        turnLabel.setText("It's " + turnController.getCurrentPlayer().getName() + "'s turn! Rounds left: " + gameEndManager.getGameLength());
    }
}
