import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Az "add player" gomb lenyomásával történő eseménykezelés.
 */
public class AddPlayerListener implements ActionListener {

    /**
     * A játékos nevét megadó text field.
     */
    private final JTextField playerNameTextBox;

    /**
     * A játékos típusát megadó combo box.
     */
    private final JComboBox<String> playerTypeComboBox;

    /**
     * A játékos hozzáadás vezérlője.
     */
    private final PlayerController playerController;

    /**
     * Létrehozza aaz esemény figyelőt, a megadott paraméterekkel.
     * @param playerNameTextBox A játékos nevét megadó text field.
     * @param playerTypeComboBox A játékos típusát megadó combo box.
     * @param playerController A játékos hozzáadás vezérlője.
     */
    public AddPlayerListener(JTextField playerNameTextBox, JComboBox<String> playerTypeComboBox, PlayerController playerController) {
        this.playerNameTextBox = playerNameTextBox;
        this.playerTypeComboBox = playerTypeComboBox;
        this.playerController = playerController;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (playerTypeComboBox.getSelectedItem() == null || playerNameTextBox.getText().isBlank())
            return;

        playerController.createPlayer(playerTypeComboBox.getSelectedItem().toString(), playerNameTextBox.getText());
    }
}
