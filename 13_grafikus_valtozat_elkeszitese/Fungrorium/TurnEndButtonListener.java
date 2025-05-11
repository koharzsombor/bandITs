import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Kör végét jelző gomb "figyelője".
 */
public class TurnEndButtonListener implements ActionListener {
    /**
     * A körök menetének controllerje.
     */
    private final TurnController turnController;

    /**
     * Létrehozáshoz, szükséges a köröket vezérlő controller megadása.
     *
     * @param turnController A körök menetének controllerje.
     */
    public TurnEndButtonListener(TurnController turnController) {
        this.turnController = turnController;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        turnController.endTurn();
    }
}
