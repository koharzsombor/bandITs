import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A Swing objectum az Insecthez
 */
public class SwingInsect extends JButton implements Updatable{
    /**
     * InsectView variable, ami eltárolja a swing (View) objectumhoz tartozó Model objectumot
     */
    private InsectView iv;

    /**
     * JPopupMenu, ami megjelenik ha rákattintunk a gombra
     */
    private JPopupMenu insectPopupMenu;

    /**
     * Konstructor, ami létrehozza a PopupMenu-t, a gombokat, a listenereket stb
     * @param i a modellben, a swing objectnek megfelelő Insect
     */
    SwingInsect(Insect i) {
        this.iv = i;

        update();

        insectPopupMenu = new JPopupMenu();

        JLabel label = new JLabel("Insect: " + ObjectRegistry.lookupName(iv));
        insectPopupMenu.add(label);

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
        String name = "Insect: " + ObjectRegistry.lookupName(iv);
        int remainingMoves = iv.getRemainingMoves();
        String state = iv.getState().name();
        int effectTimer = iv.getEffectTimer();
        setText(name);
        setToolTipText("Remaining moves for the turn: " + remainingMoves + " | Actual effect: " + state + ", rounds left: " + effectTimer);
    }

    /**
     * Megmutatja a PopupMenu-t
     * @param e a mousevent, ami meghivta
     */
    public void showPopupMenu(MouseEvent e) {
        insectPopupMenu.remove(0);
        JLabel label = new JLabel("Insect: " + ObjectRegistry.lookupName(iv));
        insectPopupMenu.add(label, 0);
        insectPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
