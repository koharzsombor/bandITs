
public interface Mycelium extends MyceliumControl, MyceliumView, Mushroom{
    /**
     * A gombafonál elvágódik, ezzel szól a többi gombafonálnak, hogy nézzék meg, hogy hozzá vannak-e csatlakoztatva
     * egy gombatesthez.
     */
    public void cutImmediate();

    /**
     * Beállítja a deathTimer-t 2-re
     */
    public void cutWithDelay();

    int getGrowTimer();
}
