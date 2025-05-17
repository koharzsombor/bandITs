import java.awt.event.*;

/**
 * MoveButton gombra eseménykezelő osztály
 */
public class MoveButtonMouseListener implements MouseListener {
    /**
     * MoveButton, amihez tartozik
     */
    MoveButton moveButton;

    /**
     * Konstructor
     * @param mb MoveButton amihez tartozik a listener
     */
    MoveButtonMouseListener(MoveButton mb){
        moveButton = mb;
    }

    /**
     * A tényleges működése a gombnak, azak szól az InsectControllernek, hogy az insect elvágna egy myceliumot
     * @param e feldolgozandó event
     */
    public void mouseReleased(MouseEvent e) {
        moveButton.showPopupMenu(e);
    }

    /**
     * MouseListener osztály implementációa
     * @param e feldolgozandó event
     */
    public void mousePressed(MouseEvent e) {}

    /**
     * MouseListener osztály implementációi
     * @param e feldolgozandó event
     */
    public void mouseClicked(MouseEvent e) {}

    /**
     * MouseListener osztály implementációi
     * @param e feldolgozandó event
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * MouseListener osztály implementációi
     * @param e feldolgozandó event
     */
    public void mouseExited(MouseEvent e) {}
}