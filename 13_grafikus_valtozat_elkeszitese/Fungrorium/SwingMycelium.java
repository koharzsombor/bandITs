/**
 * A Mycelium grafikusan megjeleníthető formája
 */
public class SwingMycelium implements Updatable{

    /**
     * A modelbeli párja a grafikus objektumnak
     */
    public MyceliumView mv;

    public SwingMycelium(MyceliumView mv){
        this.mv=mv;

    }

    /**
     * Frissíti a nézetetet, a hozzá tartozó modell alapján.
     */
    @Override
    public void update() {

    }
}
