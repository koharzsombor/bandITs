import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndTurnButtonListener implements ActionListener {
    private TurnController turnController;

    EndTurnButtonListener() {
        this.turnController = (TurnController) ObjectRegistry.getObject("TURN");
    }

    public void actionPerformed(ActionEvent e) {
        turnController.endTurn();
    }
}
