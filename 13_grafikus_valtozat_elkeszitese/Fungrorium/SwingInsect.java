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

        setBackground(Color.BLACK);
        addMouseListener(new InsectMouseAdapter(this));
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

        JButton endTurnButton = new JButton("End turn");
        TurnController turnController = (TurnController) ObjectRegistry.getObject("TURN");
        endTurnButton.addActionListener(new TurnEndButtonListener(turnController));
        insectPopupMenu.add(endTurnButton);
    }

    /**
     * Updates the tooltip to show Remaining moves, actual effect, and it's timer
     */
    @Override
    public void update() {
        setToolTipText("Remaining moves for the turn: " + iv.getRemainingMoves() + "\n" +
                "Actual effect: " + iv.getState().name() + ", rounds left: " + iv.getEffectTimer());
    }

    /**
     * Shows the JPopupMenu
     * @param e a mousevent, ami meghivta
     */
    public void showPopupMenu(MouseEvent e) {
        insectPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
