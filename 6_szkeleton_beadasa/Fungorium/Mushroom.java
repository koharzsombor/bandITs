/**
 * A Mushroom osztaly egy absztrakt osztaly
 *  * Definialja a novekedes es torles funkcionalitasait
 *  * Minden Mushroom tipusu objektum automatikusan egy Turn kezdetenek esemenyere reagal
 */
public abstract class Mushroom implements OnTurnBeginSubscriber {
    /**
     *  Az osztaly egyed torleset megvalosito absztrakt metodus
     *  Minden alosztalynak implementalnia kell
     */
    public abstract void delete();

    /**
     * Celja a metodust meghivo objektum novesztese egy adott tektonra
     * Minden alosztalynak meg kell hataroznia ennek a funkcionalitasat
     * @param sporeCount - A Mycelium novesztesekor szukseges, ha > 0 akkor
     *                   gyorsabban no/kevesebb Turn alatt
     */
    public abstract void grow(int sporeCount);
}
