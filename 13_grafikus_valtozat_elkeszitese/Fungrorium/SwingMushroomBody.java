import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A MushroomBodyhoz tartozó swinges nézet, amely az objektumot piros háromszögként jeleníti meg.
 */
public class SwingMushroomBody extends JPanel implements Updatable {

    private final MushroomBodyView mbv;
    private JPopupMenu mushroomBodyPopupMenu;
    private static final int TRIANGLE_MARGIN = 5;

    /**
     * Konstruktor.
     *
     * @param mushroomBody A MushroomBody példány, amelyhez a SwingMushroomBody objektum létrehozásra kerül.
     */
    public SwingMushroomBody(MushroomBody mushroomBody) {
        this.mbv = mushroomBody;

        addMouseListener(new MushroomBodyMouseAdapter(this));
        update();

        mushroomBodyPopupMenu = new JPopupMenu();
    }

    /**
     * Tooltip frissítése a megmaradt spórakilövések számával.
     */
    @Override
    public void update() {
        setToolTipText("Remaining ejects: " + mbv.getRemainingEjects());
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

        mbv.updateReachableTectons();
        JLabel label = new JLabel("MushroomBody: " + ObjectRegistry.lookupName(mbv));
        mushroomBodyPopupMenu.add(label);

        JLabel subLabel = new JLabel("Reachable tectons for ejecting spores to:");
        mushroomBodyPopupMenu.add(subLabel);

        for (Tecton tecton : mbv.getReachableTectons()) {
            JButton button = new JButton("→ " + ObjectRegistry.lookupName(tecton));
            button.addActionListener(evt -> {
                MushroomBodyController controller =
                        (MushroomBodyController) ObjectRegistry.getObject("MushroomBodyController");
                controller.eject((MushroomBody)mbv, tecton);
            });
            mushroomBodyPopupMenu.add(button);
        }
        mushroomBodyPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * A MushroomBody grafikus megjelenítése piros háromszög megrajzolásával.
     *
     * @param g Grafikus objektum.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D mbTriangle = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        int[] xPoints = { w / 2, w - TRIANGLE_MARGIN, TRIANGLE_MARGIN };
        int[] yPoints = { TRIANGLE_MARGIN, h - TRIANGLE_MARGIN, h - TRIANGLE_MARGIN };

        mbTriangle.setColor(Color.RED);
        mbTriangle.fillPolygon(xPoints, yPoints, 3);
    }
}
