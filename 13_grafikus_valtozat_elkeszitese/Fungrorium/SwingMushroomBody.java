import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A MushroomBodyhoz tartozó swinges nézet, amely az objektumot piros háromszögként jeleníti meg.
 */
public class SwingMushroomBody extends JButton implements Updatable {

    private final MushroomBodyView mbv;
    private JPopupMenu mushroomBodyPopupMenu;

    /**
     * Konstruktor.
     *
     * @param mushroomBody A MushroomBody példány, amelyhez a SwingMushroomBody objektum létrehozásra kerül.
     */
    public SwingMushroomBody(MushroomBody mushroomBody) {
        this.mbv = mushroomBody;

        update();

        mushroomBodyPopupMenu = new JPopupMenu();

        addMouseListener(new MushroomBodyMouseAdapter(this));
    }

    /**
     * Tooltip frissítése a megmaradt spórakilövések számával (a gombatest neve is kiíródik).
     */
    @Override
    public void update() {
        String name = "MushroomBody: " + ObjectRegistry.lookupName(mbv);
        int ejects = mbv.getRemainingEjects();
        int spores = mbv.getSpores().size();
        setText(name);
        setToolTipText("Remaining ejects: " + ejects + " | Spores available to eject: " + spores);
    }

    /**
     *  Megjeleníti a MushroomBodyhoz tartozó popup menüt a gombatest nevével és
     *  az aktuálisan elérhető tektonokra mutató gombokkal.
     *  Minden gomb az adott tektonra történő spórakilövést indítja el.
     *
     * @param e MouseEvent objektum.
     */
    public void showPopupMenu(MouseEvent e) {
        mushroomBodyPopupMenu.removeAll();

        JLabel label = new JLabel("MushroomBody: " + ObjectRegistry.lookupName(mbv));
        mushroomBodyPopupMenu.add(label);

        JLabel subLabel = new JLabel("Choose one of the following reachable tectons to eject spores to:");
        mushroomBodyPopupMenu.add(subLabel);

        for (Tecton tecton : mbv.getReachableTectons()) {
            JButton button = new JButton("-> " + ObjectRegistry.lookupName(tecton));
            button.addActionListener(new EjectSporesButtonListener((MushroomBody)mbv, tecton));
            mushroomBodyPopupMenu.add(button);
        }
        mushroomBodyPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
