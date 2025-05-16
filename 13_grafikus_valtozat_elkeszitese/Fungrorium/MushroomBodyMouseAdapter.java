import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Az egéreseményeket kezelő osztály, amely figyeli az egér interakcióit.
 */
public class MushroomBodyMouseAdapter implements MouseListener {

    /**
     * A SwingMushroomBody objektum, amelyhez ez az egérfigyelő tartozik.
     */
    SwingMushroomBody swingMushroomBody;

    /**
     * Konstruktor, amely elmenti a hozzárendelt SwingMushroomBody példányt.
     *
     * @param smb SwingMushroomBody példány.
     */
    public MushroomBodyMouseAdapter(SwingMushroomBody smb) {
        this.swingMushroomBody = smb;
    }

    /**
     * Akkor hívódik meg, amikor az egérgombot felengedik.
     * Itt jelenik meg a SwingMushroomBody-hoz tartozó JPopupMenu.
     *
     * @param e Az eseményt leíró MouseEvent objektum.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        swingMushroomBody.showPopupMenu(e);
    }

    /**
     * Az egérgomb lenyomásakor hívódik meg, de itt nem használjuk.
     * A MouseListener megvalósítása miatt van rá szükség.
     *
     * @param e Az eseményt leíró MouseEvent objektum.
     */
    public void mousePressed(MouseEvent e) {}

    /**
     * Egérkattintás esetén hívódik meg, de itt nem használjuk.
     * A MouseListener megvalósítása miatt van rá szükség.
     *
     * @param e Az eseményt leíró MouseEvent objektum.
     */
    public void mouseClicked(MouseEvent e) {}

    /**
     * Akkor hívódik meg, amikor a kurzor belép a komponens területére, de itt nem használjuk.
     * A MouseListener megvalósítása miatt van rá szükség.
     *
     * @param e Az eseményt leíró MouseEvent objektum.
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * Akkor hívódik meg, amikor az egérkurzor elhagyja a komponens területét, de itt nem használjuk.
     * A MouseListener megvalósítása miatt van rá szükség.
     *
     * @param e Az eseményt leíró MouseEvent objektum.
     */
    public void mouseExited(MouseEvent e) {}
}
