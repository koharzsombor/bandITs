import java.util.ArrayList;
import java.util.List;

/**
 * Értesíti a rá feliratkozott objektumokat, hogy körbeért a játékosok köre.
 */
public class RoundObserverImpl implements RoundObserver {
    /**
     * A feliratkozók listája.
     */
    private final List<RoundBeginSubscriber> subscriberList = new ArrayList<>();

    /**
     * Egy objektum értesítést kér arról, hogy a játékosok köre véget ért.
     *
     * @param subscriber Az értesítést kapó objektum.
     */
    @Override
    public void subscribe(RoundBeginSubscriber subscriber) {
        subscriberList.add(subscriber);
    }

    /**
     * Értesíti a feliratkozott objektumokat.
     */
    @Override
    public void notifySubscribers() {
        subscriberList.forEach(RoundBeginSubscriber::onRoundBegin);
    }
}
