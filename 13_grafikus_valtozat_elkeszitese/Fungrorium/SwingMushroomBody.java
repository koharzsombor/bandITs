import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A MushroomBody-hoz tartozó swinges nézet, amely piros háromszöget rajzol.
 */
public class SwingMushroomBody extends JButton implements Updatable {

    private MushroomBodyView mbv;
    private JPopupMenu mushroomBodyPopupMenu;

    /**
     * Konstruktor.
     * Beállítja az egérfigyelőt, létrehozza a popup menüt és frissíti a tooltipet.
     *
     * @param mushroomBody A MushroomBody példány, amelyhez a SwingMushroomBody objektum létrehzoásra kerül.
     */
    public SwingMushroomBody(MushroomBody mushroomBody) {
        this.mbv = mushroomBody;
        setContentAreaFilled(false);

        addMouseListener(new MushroomBodyMouseAdapter(this));
        update();

        mushroomBodyPopupMenu = new JPopupMenu();
        mushroomBodyPopupMenu.add("MushroomBody: " + ObjectRegistry.lookupName(mushroomBody));

        JButton ejectButton = new JButton("Eject spores");
        ejectButton.addActionListener(new EjectSporesButtonListener(mushroomBody));
        mushroomBodyPopupMenu.add(ejectButton);

        JButton endTurnButton = new JButton("End turn");
        TurnController turnController = (TurnController) ObjectRegistry.getObject("TURN");
        endTurnButton.addActionListener(new TurnEndButtonListener(turnController));
        mushroomBodyPopupMenu.add(endTurnButton);
    }

    /**
     * Tooltip frissítése a megmaradt spórakilövések számával.
     */
    @Override
    public void update() {
        setToolTipText("Remaining ejects: " + mbv.getRemainingEjects());
    }

    /**
     * Megjeleníti a popup menüt.
     *
     * @param e MouseEvent objektum.
     */
    public void showPopupMenu(MouseEvent e) {
        mushroomBodyPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * A MushroomBody grafikus megjelenítése: piros háromszög rajzolása.
     *
     * @param g Grafikus objektum.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        int[] xPoints = { w / 2, w - 5, 5 };
        int[] yPoints = { 5, h - 5, h - 5 };

        g2.setColor(Color.RED);
        g2.fillPolygon(xPoints, yPoints, 3);
    }
}
