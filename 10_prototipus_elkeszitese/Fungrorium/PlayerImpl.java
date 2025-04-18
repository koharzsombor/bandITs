import java.util.ArrayList;
import java.util.List;

/**
 * A játékos implementációja.
 */
public abstract class PlayerImpl implements Player {

    /**
     * Az objektumok, akiket értesíteni kell a játékos köre elején.
     */
    protected final List<TurnBeginSubscriber> turnBeginSubscribers = new ArrayList<>();

    /**
     * A játékos neve.
     */
    protected String name;

    /**
     * A játékos létrehozásához szükséges megadni a nevét.
     * @param name A játékos neve.
     */
    public PlayerImpl(String name) {
        this.name = name;
    }

    /**
     * Egy feliratkozó jelzi, hogy értesítést szeretne kapni arról, hogy a játékos köre elkezdődött.
     *
     * @param subscriber
     */
    @Override
    public void subscribe(TurnBeginSubscriber subscriber) {
        turnBeginSubscribers.add(subscriber);
    }

    /**
     * Beállítja a játékos nevét.
     *
     * @param name A játékos neve.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszadja a játékos nevét.
     *
     * @return A játékos neve.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Szól a játékos feliratkozóinak, hogy a játékosnak elkezdődött a köre.
     */
    @Override
    public void notifySubscribers() {
        for (TurnBeginSubscriber subscriber : turnBeginSubscribers) {
            subscriber.onTurnBegin();
        }
    }

    /**
     * Kiszámolja a játékos pontszámát.
     *
     * @return A játékos pontszáma.
     */
    @Override
    public abstract int calculateScore();
}
