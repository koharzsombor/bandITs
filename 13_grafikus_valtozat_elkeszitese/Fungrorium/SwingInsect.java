import javax.swing.*;

/**
 * A Swing objectum az Insecthez
 */
public class SwingInsect extends JMenu implements Updatable {
    /**
     * InsectView variable, ami eltárolja a swing (View) objectumhoz tartozó Model objectumot
     */
    private Insect iv;

    /**
     * Konstructor, ami létrehozza a PopupMenu-t, a gombokat, a listenereket stb
     * @param i a modellben, a swing objectnek megfelelő Insect
     */
    SwingInsect(Insect i) {
        super(ObjectRegistry.lookupName(i));

        this.iv = i;

        update();
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

        removeAll();
        revalidate();
        repaint();

        JLabel label = new JLabel("Insect: " + ObjectRegistry.lookupName(iv));
        add(label);

        JMenuItem eatButton = new JMenuItem("Eat a spore");
        eatButton.addActionListener(new EatButtonListener(iv));
        add(eatButton);

        JMenuItem cutButton = new JMenuItem("Cut a mycelium");
        cutButton.addActionListener(new CutButtonListener(iv));
        add(cutButton);

        MoveButton moveButton = new MoveButton("Move to a tecton", iv);
        //moveButton.addMouseListener(new MoveButtonMouseListener(moveButton));
        add(moveButton);

        addMouseListener(new InsectMouseAdapter(this));
    }

}
