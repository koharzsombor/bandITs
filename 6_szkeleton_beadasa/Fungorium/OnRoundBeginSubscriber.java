/**
 * Egy observer feliratkozója, ami akkor hívódik meg, amikor a játékos körök körbe értek.
 */
public interface OnRoundBeginSubscriber {
    /**
     * Egy függvény ami leírja, hogy mi történik, amikor a játékosok köre körbe érték.
     */
    void onRoundBegin();
}
