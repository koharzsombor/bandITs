/**
 *
 */
public class Insect {
    /**
     *
     */
    private Tecton location;

    /**
     *
     */
    private int maxMoves;

    /**
     *
     */
    private int remainingMoves;

    /**
     *
     */
    private int sporesEaten;

    /**
     *
     */
    private int effectTimer;

    /**
     *
     */
    private InsectState state;

    /**
     * @return
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * @return
     */
    public int getMaxMoves() {
        return maxMoves;
    }

    /**
     * @param maxMoves
     */
    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;
    }

    /**
     * @return
     */
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * @param remainingMoves
     */
    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }

    /**
     * @return
     */
    public int getSporesEaten() {
        return sporesEaten;
    }

    /**
     * @param sporesEaten
     */
    public void setSporesEaten(int sporesEaten) {
        this.sporesEaten = sporesEaten;
    }

    /**
     * @return
     */
    public int getEffectTimer() {
        return effectTimer;
    }

    /**
     * @param effectTimer
     */
    public void setEffectTimer(int effectTimer) {
        this.effectTimer = effectTimer;
    }

    /**
     * @return
     */
    public InsectState getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(InsectState state) {
        this.state = state;
    }

    /**
     *
     */
    public void cutMycelium() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    public void eatSpore() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param target
     */
    public void move(Tecton target) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    public void beSlow() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    public void beFast() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    public void preventCut() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    public void beStunned() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    public void runAway() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
