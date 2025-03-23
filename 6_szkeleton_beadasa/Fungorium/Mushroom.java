/**
 * A gomba egy részét ábrázoló osztály, azaz a közös ősosztálya a gombatestnek és a gombafonálnak.
 */
public abstract class Mushroom implements OnTurnBeginSubscriber {
    /**
     * A gombarész kitörlése.
public abstract class Mushroom implements OnTurnBeginSubscriber {
     */
    public abstract void delete();

    /**
     * A gombarész növekedése.
     * @param sporeCount A növekedés helyének spóraszáma.
     */
    public abstract void grow(int sporeCount);
}
