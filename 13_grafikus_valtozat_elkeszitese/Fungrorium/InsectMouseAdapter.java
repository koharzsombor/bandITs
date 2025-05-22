import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Insecthez tartozó gérkezelő osztály
 */
public class InsectMouseAdapter implements MouseListener {
    /**
     * Swing Insect objectum, akihez tartozik
     */
    SwingInsect swingInsect;

    /**
     *Konstruktor
     * @param si az eltárolni kivánt SwingInsect
     */
    InsectMouseAdapter(SwingInsect si) {
        this.swingInsect = si;
    }

    /**
     * MouseListener osztály implementációi. Megjeleniti a PopupMenu-t a SwingInsectben
     * @param e the event to be processed
     */
    public void mouseReleased(MouseEvent e) {
        //swingInsect.showPopupMenu(e);
    }

    /**
     * MouseListener osztály implementációa
     * @param e the event to be processed
     */
    public void mousePressed(MouseEvent e) {}

    /**
     * MouseListener osztály implementációi
     * @param e the event to be processed
     */
    public void mouseClicked(MouseEvent e) {}

    /**
     * MouseListener osztály implementációi
     * @param e the event to be processed
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * MouseListener osztály implementációi
     * @param e the event to be processed
     */
    public void mouseExited(MouseEvent e) {}

}
