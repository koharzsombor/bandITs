import java.awt.event.*;

/**
 * Move gombra eseménykezelő osztály
 */
public class MoveButtonMouseListener implements MouseListener {
    MoveButton moveButton;

    MoveButtonMouseListener(MoveButton mb){
        moveButton = mb;
    }

    /**
     * A tényleges működése a gombnak, azak szól az InsectControllernek, hogy az insect elvágna egy myceliumot
     * @param e the event to be processed
     */
    public void mouseReleased(MouseEvent e) {
        moveButton.showPopupMenu(e);
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