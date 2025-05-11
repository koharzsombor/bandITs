import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A játék indításáról gondoskodó menü.
 */
public class GameStartView extends JPanel {
    /**
     * @param appFrame
     * @param mapCreationController
     * @param gameEndManager
     * @param turnController
     */
    public GameStartView(AppFrame appFrame, MapCreationController mapCreationController,
                         GameEndManager gameEndManager, TurnController turnController) {

        setBorder(new EmptyBorder(5, 5, 5, 5)); //5 px padding

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel gameLengthLabel = new JLabel("Game Length");
        gameLengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gameLengthLabel);

        add(Box.createVerticalStrut(10)); // padding alá

        JTextField gameLengthTextBox = new JTextField();
        gameLengthTextBox.setMaximumSize(new Dimension(50, 40));
        gameLengthTextBox.setPreferredSize(new Dimension(50, 40));
        gameLengthTextBox.setHorizontalAlignment(SwingConstants.CENTER);
        gameLengthTextBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gameLengthTextBox);

        add(Box.createVerticalStrut(10));
        gameLengthTextBox.setPreferredSize(new Dimension(50, 100));

        JButton gameStartButton = new JButton("Start Game");
        gameStartButton.setSize(200, 500);
        gameStartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStartButton.addActionListener(
                new StartGameListener(mapCreationController, appFrame, gameLengthTextBox, gameEndManager, turnController));

        add(gameStartButton);
    }
}
