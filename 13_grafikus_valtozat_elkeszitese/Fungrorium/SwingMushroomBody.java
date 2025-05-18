import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A MushroomBodyhoz tartozó swinges nézet, amely az objektumot piros háromszögként jeleníti meg.
 */
public class SwingMushroomBody extends JPanel implements Updatable {

    private final MushroomBodyView mbv;
    private JPopupMenu mushroomBodyPopupMenu;
    private static final int TRIANGLE_MARGIN = 1;
    private static final int TRIANGLE_MARGIN_SIDES = 6;
    int width = 50;
    int height = 42;
    int nPoints = 3;
    private Color triangleColor = Color.RED;

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
     * Tooltip frissítése a megmaradt spórakilövések számával (a gombatest neve is kiíródik).
     */
    @Override
    public void update() {
        String name = ObjectRegistry.lookupName(mbv);
        int ejects = mbv.getRemainingEjects();
        int spores = mbv.getSpores().size();
        setToolTipText("MushroomBody: " + name + " | Remaining ejects: " + ejects + " | Spores available to eject: " + spores);
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
            button.addActionListener(new EjectSporesButtonListener((MushroomBody)mbv));
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

        int[] xPoints = { width / 2, width - TRIANGLE_MARGIN_SIDES, TRIANGLE_MARGIN_SIDES };
        int[] yPoints = { TRIANGLE_MARGIN, height - TRIANGLE_MARGIN, height - TRIANGLE_MARGIN };

        mbTriangle.setColor(triangleColor);
        mbTriangle.fillPolygon(xPoints, yPoints, nPoints);

    }
}
