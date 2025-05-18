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
    public void update() {}
}
