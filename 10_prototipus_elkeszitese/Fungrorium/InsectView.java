/**
 * A Viewban használt Rovar interfész.
 */
public interface InsectView {
    /**
     * A rovar jelenlegi tartózkodási helyének getterje.
     * @return A rovar jelenlegi tartózkodási helye.
     */
    public Tecton getLocation();

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt getterje.
     * @return A maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    public int getMaxMoves();

    /**
     * A körben lévő maradék lépések számának lekérdezése
     * @return - A körben lévő maradék lépéseket adja vissza
     */
    public int getRemainingMoves();

    /**
     * A játk alatt elfogyasztott spórák számának lekérdezése
     * @return
     */
    public int getSporesEaten();

    /**
     * A jelenlegi állapotnak a maradék idejének getterje
     * @return A jelenlegi állapotnak a maradék ideje.
     */
    public int getEffectTimer();

    /**
     * A rovar állapotának getterje.
     * @return A rovar jelenlegi állapota.
     */
    public InsectState getState();

    /**
     * A rovar szakadásainak számlálójának a gettere.
     * @return A rovar szakadásainak száma.
     */
    public int getSplitNum();
}
