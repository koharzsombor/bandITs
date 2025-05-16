import javax.swing.*;
import java.awt.event.MouseEvent;

public class MoveButton extends JButton {
    Insect insect;

    JPopupMenu popup;

    MoveButton(String text, Insect insect){
        super(text);
        this.insect = insect;
    }

    /**
     * Shows the JPopupMenu
     * @param e a mousevent, ami meghivta
     */
    public void showPopupMenu(MouseEvent e) {
        Tecton locationTV = insect.getLocation();
        for(Tecton t : locationTV.getNeighbours()){
            JButton button = new JButton(ObjectRegistry.lookupName(t));
            button.addActionListener(new TectonChoosingButtonListener(insect, t, popup));
            popup.add(button);
        }
        popup.show(e.getComponent(), e.getX(), e.getY());
    }
}
