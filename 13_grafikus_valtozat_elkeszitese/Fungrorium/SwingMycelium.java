import javax.swing.*;
import java.awt.*;

/**
 * A Mycelium grafikusan megjeleníthető formája
 */
public class SwingMycelium implements Updatable{

    private final JComponent parent;

    private final int size = 80;
    private final int growingSize = size - 20;
    private final Color fillColor = new Color(166, 80, 42);

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

    public void draw(Graphics g) {
        if (mv.getLocation() == null) return;

        Graphics2D g2 = (Graphics2D) g;
        Point pos = mv.getLocation();                   //Ide kell valami átváltás vagy egyéb helyről változó
        g.setColor(fillColor);

        if(mv.isGrowing()){
            g.fillOval(pos.x + 5, pos.y + 5, growingSize, growingSize);   //Nem jó
            return;
        }
        g.fillOval(pos.x + 5, pos.y + 5, size, size);   //Nem jó
    }
}
