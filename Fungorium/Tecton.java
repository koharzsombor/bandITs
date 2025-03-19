import java.util.List;

/**
 *
 */
public abstract class Tecton {
    /**
     *
     */
    private int breakTimer = 0;

    /**
     *
     */
    private List<Tecton> neighbours;

    /**
     *
     */
    private int myceliumCapacity = 0;


    /**
     * @return
     */
    public int getBreakTimer() {
        return breakTimer;
    }

    /**
     * @param breakTimer
     */
    public void setBreakTimer(int breakTimer) {
        this.breakTimer = breakTimer;
    }

    /**
     * @return
     */
    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    /**
     * @param neighbours
     */
    public void setNeighbours(List<Tecton> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * @return
     */
    public int getMyceliaCapacity() {
        return myceliumCapacity;
    }

    /**
     * @param myceliaCapacity
     */
    public void setMyceliaCapacity(int myceliaCapacity) {
        this.myceliumCapacity = myceliaCapacity;
    }

    /**
     * @return
     */
    public int getMyceliumCapacity() {
        return myceliumCapacity;
    }

    /**
     * @param myceliumCapacity
     */
    public void setMyceliumCapacity(int myceliumCapacity) {
        this.myceliumCapacity = myceliumCapacity;
    }
}
