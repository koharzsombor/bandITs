import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public abstract class Tecton implements OnRoundBeginSubscriber {
    /**
     *
     */
    private int breakTimer = 0;

    /**
     *
     */
    private List<Tecton> neighbours = new ArrayList<>();

    /**
     *
     */
    private int myceliumCapacity = 0;

    /**
     *
     */
    private Queue<Spore> spores = new LinkedList<>();

    /**
     *
     */
    private MushroomBody mushroomBody;

    /**
     *
     */
    private Queue<Mycelium> mycelia = new LinkedList<>();

    /**
     *
     */
    private List<Insect> occupants = new ArrayList<>();

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
     * @param myceliumCapacity
     */
    public void setMyceliumCapacity(int myceliumCapacity) {
        this.myceliumCapacity = myceliumCapacity;
    }

    /**
     * @return
     */
    public Queue<Spore> getSpores() {
        return spores;
    }

    /**
     * @param spores
     */
    public void setSpores(Queue<Spore> spores) {
        this.spores = spores;
    }

    /**
     * @return
     */
    public MushroomBody getMushroomBody() {
        return mushroomBody;
    }

    /**
     * @param mushroomBody
     */
    public void setMushroomBody(MushroomBody mushroomBody) {
        this.mushroomBody = mushroomBody;
    }

    /**
     * @return
     */
    public Queue<Mycelium> getMycelia() {
        return mycelia;
    }

    /**
     * @param mycelia
     */
    public void setMycelia(Queue<Mycelium> mycelia) {
        this.mycelia = mycelia;
    }

    /**
     * @param tecton
     * @return
     */
    public int distance(Tecton tecton) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return
     */
    public boolean checkNeighbourMyceliaSustain() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return
     */
    public boolean myceliaCheckSustain() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return
     */
    public List<Insect> getOccupants() {
        return occupants;
    }

    /**
     * @param occupants
     */
    public void setOccupants(List<Insect> occupants) {
        this.occupants = occupants;
    }

    /**
     * @param insect
     */
    public void addOccupant(Insect insect) {
        occupants.add(insect);
    }

    /**
     * @param insect
     */
    public void removeOccupant(Insect insect) {
        occupants.remove(insect);
    }

    /**
     * @return
     */
    public boolean hasMycelium() {
        return !mycelia.isEmpty();
    }

    /**
     * @param mycelium
     */
    public void addMycelium(Mycelium mycelium) {
        throw new UnsupportedOperationException("Not implemented");
    }


    /**
     * @param spore
     */
    public void addSpore(Spore spore) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param tecton
     */
    public void addNeighbour(Tecton tecton) {
        neighbours.add(tecton);
    }

    /**
     * @param MyceliumGrowthEvaluator
     * @param mycelium
     */
    public abstract void accept(MyceliumGrowthEvaluator MyceliumGrowthEvaluator, Mycelium mycelium);

    /**
     * @param mushroomBodyGrowthEvaluator
     * @param mushroomBody
     */
    public abstract void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody);
}
