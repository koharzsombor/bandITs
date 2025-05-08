import javax.swing.*;

/**
 * A játék indításáról gondoskodó menü.
 */
public class GameStartView extends JLabel {
    private JTextField gameLengthTextBox;

    public GameStartView() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel gameLengthLabel = new JLabel("Game Length");
        add(gameLengthLabel);
        add(Box.createVerticalStrut(5)); // padding alá

        gameLengthTextBox = new JTextField(5);
        add(gameLengthTextBox);
        add(Box.createVerticalStrut(20));

        JButton gameStartButton = new JButton("Start Game");
        add(gameStartButton);
    }
}
