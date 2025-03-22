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
    private int maxMoves = 2;

    /**
     *
     */
    private int remainingMoves = 0;

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
    private InsectState state = InsectState.NORMAL;

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
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=eatSpore(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getLocation()));
        }

        getLocation().eatSpore(this);
    }

    /**
     * @param target
     */
    public void move(Tecton target) {
        if(Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=moveInsect(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getLocation()), Main.objectNames.get(target));
        }

        target.moveInsect(this, location);
    }

    /**
     *
     */
    public void beSlow() {
        setState(InsectState.SLOW);
    }

    /**
     *
     */
    public void beFast() {
        setState(InsectState.FAST);
    }

    /**
     *
     */
    public void preventCut() {
        setState(InsectState.CANNOT_CUT);
    }

    /**
     *
     */
    public void beStunned() {
        setState(InsectState.STUN);
    }

    /**
     *
     */
    public void runAway() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
