import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Move gomb
 */
public class MoveButton extends JButton {
    /**
     * Insect akihez a gomb tartozik
     */
    Insect insect;

    /**
     * PopupMenu, amiben a gomb van
     */
    JPopupMenu popup;

    /**
     * Konstruktor
     * @param text gombon megjelenő szöveg
     * @param insect Insect ami tartozik a gombhoz
     */
    MoveButton(String text, Insect insect){
        super(text);
        this.insect = insect;
        popup = new JPopupMenu();
    }

    /**
     * Létrehozza és kirajzolja a PopupMenut, amin a tektonok jelennek meg, amire mozoghat
     * @param e a mousevent, ami meghivta
     */
    public void showPopupMenu(MouseEvent e) {
        popup.removeAll();

        popup.add(new JLabel("Choose a neighbouring tecton to move to"));
        Tecton location = insect.getLocation();
        for(Tecton t : location.getNeighbours()){
            JButton button = new JButton(ObjectRegistry.lookupName(t));
            button.addActionListener(new TectonChoosingButtonListener(insect, t));
            popup.add(button);
        }

        popup.show(e.getComponent(), e.getX(), e.getY());
    }
}
