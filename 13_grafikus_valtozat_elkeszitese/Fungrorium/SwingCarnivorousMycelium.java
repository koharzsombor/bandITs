import java.awt.*;

/**
 * A CarnivorousMycelium grafikusan megjeleníthető formája
 */
public class SwingCarnivorousMycelium extends SwingMycelium{

    /**
     * Konstruktor amiben inincializáljuk a modellbeli elemet ami alapján tudjuk frissíteni a grafikus felületet.
     * Felülírjuk a sima Mycelium színét.
     * @param mv A grafikus elem modellbeli párja
     */
    public SwingCarnivorousMycelium(MyceliumView mv){
        super(mv);
    }
}
