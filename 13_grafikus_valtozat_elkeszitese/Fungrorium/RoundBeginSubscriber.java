/**
 * Olyan objetum, ami értesítést kér arról, ha a játékosok köre körbeért.
 */
public interface RoundBeginSubscriber {
    /**
     * Leírja, hogy mi történjen amikor a játékosok köre körbeért.
     */
    void onRoundBegin();
}
