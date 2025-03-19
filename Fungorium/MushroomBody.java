/**
 *
 */
public class MushroomBody extends Mushroom {

    /**
     *
     */
    private int remainingEjects;

    /**
     *
     */
    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param sporeCount
     */
    @Override
    public void grow(int sporeCount) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    @Override
    public void onTurnBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return
     */
    public int getRemainingEjects() {
        return remainingEjects;
    }

    /**
     * @param remainingEjects
     */
    public void setRemainingEjects(int remainingEjects) {
        this.remainingEjects = remainingEjects;
    }

    /**
     * @param target
     */
    public void ejectSpores(Tecton target) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
