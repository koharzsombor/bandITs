import javax.swing.*;
import java.awt.*;

/**
 * A Mycelium grafikusan megjeleníthető formája
 */
public class SwingMycelium extends JPanel implements Updatable{

    /**
     * A Myceliumot reprezentáló kör mérete
     */
    private final int size = 50;

    /**
     * A Myceliumot reprezentáló kör színe
     */
    protected Color fillColor = new Color(166, 80, 42);

    /**
     * A modelbeli párja a grafikus objektumnak
     */
    protected final MyceliumView mv;

    /**
     * Konstruktor amiben inincializáljuk a modellbeli elemet ami alapján tudjuk frissíteni a grafikus felületet
     * @param mv A grafikus elem modellbeli párja
     */
    public SwingMycelium(MyceliumView mv){
        this.mv = mv;
    }

    @Override
    public void update() {
    }

    /**
     * A Myceliumot reprezentáló kör kirajzolása
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        if (mv.getLocation() == null) return;

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(new Color(0,0,0,0));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(fillColor);
        g.fillOval(0, 0, size, size);
    }
}
