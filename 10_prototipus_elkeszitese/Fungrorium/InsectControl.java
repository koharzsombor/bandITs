/**
 * A kontrollerben használt Rovar interfész.
 */
public interface InsectControl extends TurnBeginSubscriber {
    /**
     * A rovar jelenlegi tartózkodási helyének setterje.
     * @param location A rovar új tartózkodási helye.
     */
    public void setLocation(Tecton location);

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt setterje.
     * @param maxMoves Az új maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    public void setMaxMoves(int maxMoves);

    /**
     * Bealltja a maradék lépéseket a körre
     * @param remainingMoves - Az új maradék lépések száma
     */
    public void setRemainingMoves(int remainingMoves);

    /**
     * A játék alatt elfogyasztott spórák számának frissitése
     * @param sporesEaten - Az új spóramennyiség amely el volt fogyasztva
     */
    public void setSporesEaten(int sporesEaten);

    /**
     * A jelenlegi állapotnak a maradék idejének setterje.
     * @param effectTimer A jelenlegi állapotnak a maradék idejének új értéke.
     */
    public void setEffectTimer(int effectTimer);

    /**
     * A rovar állapotának setterjeS.
     * @param state A rovar új állapota.
     */
    public void setState(InsectState state);

    /**
     * A rovar szakadásainak számlálójának settere.
     * @param splitNum A szakadások új száma.
     */
    public void setSplitNum(int splitNum);

    /**
     * Elvág egy gombafonalat a jelenlegi tartózkodási helyén.
     */
    public void cutMycelium();

    /**
     * A rovar megesz egy spórát,
     */
    public void eatSpore();

    /**
     * A rovar elmozdul a megadott céltektonra, ha van elég lépése a rovarnak.
     * @param target A cél tekton.
     */
    public void move(Tecton target);
}
