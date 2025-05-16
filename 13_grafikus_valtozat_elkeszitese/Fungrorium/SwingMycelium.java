import javax.swing.*;
import java.awt.*;

/**
 * A Mycelium grafikusan megjeleníthető formája
 */
public class SwingMycelium implements Updatable{

    private final JComponent parent;

    /**
     * A modelbeli párja a grafikus objektumnak
     */
    private final MyceliumView mv;

    public SwingMycelium(MyceliumView mv, JComponent parent){
        this.mv = mv;
        this.parent = parent;
    }

    /**
     * Frissíti a nézetetet, a hozzá tartozó modell alapján.
     */
    @Override
    public void update() {
        parent.repaint();
    }

    public void draw(Graphics2D g) {
        if (mv.getLocation() == null) return;

        Point pos = mv.getLocation();                   //Ide kell valami átváltás vagy egyéb helyről változó
        g.setColor(new Color(139, 69, 19));
        g.fillOval(pos.x + 5, pos.y + 5, 40, 40);
    }
}
