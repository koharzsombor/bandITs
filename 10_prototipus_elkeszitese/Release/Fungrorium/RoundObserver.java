/**
 * Felelőssége értesíteni a rá feliratkozó objektumokat, ha a játékosok köre körbeért.
 */
public interface RoundObserver {
    /**
     * Egy objektum értesítést kér arról, hogy a játékosok köre véget ért.
     * @param subscriber Az értesítést kapó objektum.
     */
    void subscribe(RoundBeginSubscriber subscriber);

    /**
     * Értesíti a feliratkozott objektumokat.
     */
    void notifySubscribers();
}
