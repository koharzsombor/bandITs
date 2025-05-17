import javax.swing.*;
import java.awt.*;

/**
 * A Mycelium grafikusan megjeleníthető formája
 */
public class SwingMycelium extends JPanel implements Updatable{


    private final int size = 40;
    private final int growingSize = size - 20;
    protected Color fillColor = new Color(166, 80, 42);

    /**
     * A modelbeli párja a grafikus objektumnak
     */
    private final MyceliumView mv;

    /**
     * Konstruktor amiben inincializáljuk a modellbeli elemet ami alapján tudjuk frissíteni a grafikus felületet
     * @param mv A grafikus elem modellbeli párja
     */
    public SwingMycelium(MyceliumView mv){
        this.mv = mv;
    }

    /**
     * Frissíti a nézetetet, a hozzá tartozó modell alapján.
     */
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
        g.setColor(fillColor);

        if(mv.isGrowing()){                                             //Lehetne popup menüvel is
            g.fillOval(0, 0, growingSize, growingSize);
            return;
        }

        g.fillOval(0, 0, size, size);
    }
}
