import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A nézet, amely a játékosok hozzáadásnak a folyamatát jeleníti meg.
 */
public class PlayerView extends JPanel {
    /**
     *
     */
    private final JTextField playerNameTextField;

    /**
     *
     */
    private final JComboBox<String> playerTypeComboBox;

    /**
     *
     */
    private final JButton addPlayerButton;

    /**
     *
     */
    private final PlayerContainerView playerContainerView;

    /**
     *
     */
    private final PlayerController playerController;

    public PlayerView(PlayerContainerView playerContainerView, PlayerController playerController) {
        this.playerContainerView = playerContainerView;
        this.playerController = playerController;

        setBorder(new EmptyBorder(5, 5, 5, 5)); //5 px padding
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        playerNameTextField = new JTextField();
        playerNameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, playerNameTextField.getPreferredSize().height));
        playerNameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerTypeComboBox = new JComboBox<>();
        playerTypeComboBox.addItem("Mycologist");
        playerTypeComboBox.addItem("Entomologist");
        playerTypeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, playerNameTextField.getPreferredSize().height));
        playerTypeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(new AddPlayerListener(playerNameTextField, playerTypeComboBox, playerController));
        addPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane containerScrollPane = new JScrollPane(playerContainerView);
        containerScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel playersLabel = new JLabel("Players:");
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel playerNameLabel = new JLabel("Player name:");
        playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel playerTypeLabel = new JLabel("Player type:");
        playerTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(playersLabel);
        add(containerScrollPane);
        add(Box.createVerticalStrut(5)); //5 px space
        add(playerNameLabel);
        add(playerNameTextField);
        add(playerTypeLabel);
        add(playerTypeComboBox);
        add(Box.createVerticalStrut(5)); //5 px space
        add(addPlayerButton);
    }
}
