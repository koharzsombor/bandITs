/**
 * Egy observer feliratkozója, ami akkor hívódik meg, amikor egy játékosnak köre van.
 */
public interface OnTurnBeginSubscriber {
    /**
     * Egy függvény ami leírja, hogy mi történik, amikor a játékosok köre körbe érték.
     */
    void onTurnBegin();
}
