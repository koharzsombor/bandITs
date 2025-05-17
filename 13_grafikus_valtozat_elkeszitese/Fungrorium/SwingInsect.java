import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A Swing objectum az Insecthez
 */
public class SwingInsect extends JPanel implements Updatable{
    /**
     * InsectView variable, ami eltárolja a swing (View) objectumhoz tartozó Model objectumot
     */
    private InsectView iv;

    /**
     * JPopupMenu, ami megjelenik ha rákattintunk a gombra
     */
    private JPopupMenu insectPopupMenu;

    /**
     * Kör szine
     */
    private final Color color = Color.black;

    /**
     * Kör szélessége
     */
    private final int width = getWidth(); //10 by default

    /**
     * Kör magassága
     */
    private final int height = getHeight(); //10 by default

    /**
     * Konstructor, ami létrehozza a PopupMenu-t, a gombokat, a listenereket stb
     * @param i a modellben, a swing objectnek megfelelő Insect
     */
    SwingInsect(Insect i) {
        this.iv = i;

        update();

        insectPopupMenu = new JPopupMenu();
        insectPopupMenu.add("Insect: " + ObjectRegistry.lookupName(i));

        JButton eatButton = new JButton("Eat a spore");
        eatButton.addActionListener(new EatButtonListener(i));
        insectPopupMenu.add(eatButton);

        JButton cutButton = new JButton("Cut a mycelium");
        cutButton.addActionListener(new CutButtonListener(i));
        insectPopupMenu.add(cutButton);

        MoveButton moveButton = new MoveButton("Move to a tecton", i);
        moveButton.addMouseListener(new MoveButtonMouseListener(moveButton));
        insectPopupMenu.add(moveButton);

        addMouseListener(new InsectMouseAdapter(this));
    }

    /**
     * Frissiti a toolTip-et
     */
    @Override
    public void update() {
        setToolTipText("Insect: " + ObjectRegistry.lookupName(iv) + "\n" +
                "Remaining moves for the turn: " + iv.getRemainingMoves() + "\n" +
                "Actual effect: " + iv.getState().name() + ", rounds left: " + iv.getEffectTimer());
    }

    /**
     * Kör kirajzolása
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillOval(0, 0, width, height);
    }

    /**
     * Megmutatja a PopupMenu-t
     * @param e a mousevent, ami meghivta
     */
    public void showPopupMenu(MouseEvent e) {
        insectPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
