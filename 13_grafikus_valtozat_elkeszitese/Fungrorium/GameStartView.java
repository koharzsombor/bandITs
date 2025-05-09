import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A játék indításáról gondoskodó menü.
 */
public class GameStartView extends JPanel {

    private JTextField gameLengthTextBox;

    private AppFrame appFrame;

    public GameStartView(AppFrame appFrame) {
        this.appFrame = appFrame;

        setBorder(new EmptyBorder(5, 5, 5, 5)); //5 px padding

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel gameLengthLabel = new JLabel("Game Length");
        gameLengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gameLengthLabel);

        add(Box.createVerticalStrut(10)); // padding alá

        gameLengthTextBox = new JTextField();
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
        add(gameStartButton);
    }
}
