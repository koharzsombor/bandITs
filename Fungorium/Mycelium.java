/**
 *
 */
public class Mycelium extends Mushroom {
    /**
     *
     */
    private boolean growing = true;

    /**
     *
     */
    private Tecton location;

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
    public boolean isGrowing() {
        return growing;
    }

    /**
     * @param growing
     */
    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    /**
     *
     */
    public void cut() {
        throw new UnsupportedOperationException("Not implemented");
    }

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
}
