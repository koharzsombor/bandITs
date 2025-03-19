/**
 *
 */
public abstract class Mushroom implements OnTurnBeginSubscriber {
    /**
     *
     */
    public abstract void delete();

    /**
     * @param sporeCount
     */
    public abstract void grow(int sporeCount);
}
