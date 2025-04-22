import java.util.List;
import java.util.Queue;

/**
 * A kontrollerben használt tekton interfész.
 */
public interface TectonControl {
    /**
     * Beállítja a tekton törésvisszaszámlálóját.
     * @param breakTimer A törésvviszaszámláló új értéke.
     */
    void setBreakTimer(int breakTimer);

    /**
     * Beállítja a szomszédjait a bektonnak.
     * @param neighbours A tekton új szomszédjai.
     */
    void setNeighbours(List<Tecton> neighbours);

    /**
     * Beállítja a tekton gomafonál kapacítását.
     * @param myceliaCapacity A gombafonál kapacítás új értéke.
     */
    void setMyceliaCapacity(int myceliaCapacity);

    /**
     * Beállítja a tekton spóráit.
     * @param spores A tekton új spórái.
     */
    void setSpores(Queue<Spore> spores);

    /**
     * Beállítja a tekon gombatestét a megadott gombatestre.
     * @param mushroomBody A tekton új gombateste.
     */
    void setMushroomBody(MushroomBody mushroomBody);

    /**
     * Beállítja a tektonon lévő gombafonalakat.
     * @param mycelia A tekton új gombafonalai.
     */
    void setMycelia(Queue<Mycelium> mycelia);

    /**
     * Beállítja az éppen a tektonon tartozkodó rovarok listáját.
     * @param occupants A tekonon tartózkodó rovarok új listája.
     */
    void setOccupants(List<Insect> occupants);

    /**
     * Hozzáad egy rovart a tekonon tartózkodó rovarok listájához.
     * @param insect A tektonon újonnan tartózkodó rovar.
     */
    void addOccupant(Insect insect);

    /**
     * Levesz egy rovart a tekonon tartózkodó rovarok listájáról.
     * @param insect A rovar, amit le szeretnénk szedni a listáról.
     */
    void removeOccupant(Insect insect);

    /**
     * Hozzáad egy gombafonált a tektonon lévő gombafonalakhoz.
     * @param mycelium Az új gombafonál.
     */
    void addMycelium(Mycelium mycelium);

    /**
     * Hozzáad egy spórát a tektonhoz.
     * @param spore A tekonra érkező új spóra.
     */
    void addSpore(Spore spore);

    /**
     * A tektonnak ad egy új szomszédot.
     * @param neighbour A tekton új szomszédja.
     */
    void addNeighbour(Tecton neighbour);

    void eatSpore(Insect insect);

    void moveInsect(Insect insect, Tecton insectLocation);
}
