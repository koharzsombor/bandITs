import java.util.*;

/**
 * A tekton implementációja.
 */
public abstract class TectonImpl implements Tecton {
    /**
     * Az időzitő amely ha elér 0-ra a tekton eltörik.
     */
    private int breakTimer;

    /**
     * A tektonnal szomszédos tektonok listája.
     */
    private final List<Tecton> neighbours = new ArrayList<>();

    /**
     * A gombafonalak maximum száma, ami elfér a tektonon.
     */
    private int myceliaCapacity;

    /**
     * A tektonon lévő spórák.
     */
    private final Queue<Spore> spores = new LinkedList<>();

    /**
     * A tektonon lévő gombatest.
     */
    private MushroomBody mushroomBody;

    /**
     * A tektonon lévő gombafonalak.
     */
    private final Queue<Mycelium> mycelia = new LinkedList<>();

    /**
     * A tektonon lévő rovarok.
     */
    private final List<Insect> occupants = new ArrayList<>();

    /**
     * Olyan tektonon listája, aminek a gombafonala nincs táplálva.
     */
    private final Set<Tecton> notSustained = new HashSet<>();

    /**
     * Meghatározza a távolságot a jelen tekton és a megadott tekton között.
     *
     * @param tecton A megadott tekton, amitől a távolságot számoljuk.
     * @return A két tekton közti távolság.
     */
    @Override
    public int distance(Tecton tecton) {
        return 0;
    }

    /**
     * Ellenörzi, hogy a szomszédos gombafonállal rendelkező tektonok a gombafonalakat valami
     * életben tartja-e. Ha nem tartja életben semmi akkor kitörli őket.
     */
    @Override
    public void checkNeighbourMyceliaSustain() {

    }

    /**
     * Hozzáad egyszerre több spórát a tektonhoz.
     *
     * @param newSpores A spórák listája, amiket hozzáadunk az új tektonhoz.
     */
    @Override
    public void transferSpores(List<Spore> newSpores) {

    }

    /**
     * Eldönti, hogy az adott gombafonál nőhet-e ezen a tektonon
     *
     * @param myceliumGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mycelium                A gombafonál, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    @Override
    public abstract void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium);

    /**
     * Eldönti, hogy az adott gombatest nőhet-e ezen a tektonon.
     *
     * @param mushroomBodyGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mushroomBody                A gombatest, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    @Override
    public abstract void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody);

    /**
     * Eldönti, hogy a tekton képes-e önerőből fenntartani egy gombafonalat.
     *
     * @return A tekton képes-e önerőből fenntartani egy gombafonalat.
     */
    @Override
    public abstract boolean sustaining();

    /**
     * Megpróbál minden rajta lévő rovart eltávolítani.
     */
    @Override
    public void killOccupants() {

    }

    /**
     * A megadott rovar megeszi a tektonon lévő, legrégebben ott tartózkodó spórát.
     *
     * @param insect A spórát elfogyasztó rovar.
     */
    @Override
    public void eatSpore(Insect insect) {

    }

    /**
     * A tektonon lévő gombafonál elrágása.
     */
    @Override
    public void cutMycelium() {

    }

    /**
     * Ha tud a rovar a jelen tectonra mozogni, akkor megcsinálja ezt a műveletet.
     *
     * @param insect A rovar, akit mozgat a függvény.
     */
    @Override
    public void moveInsect(Insect insect) {

    }

    /**
     * Beállítja a tekton törésvisszaszámlálóját.
     *
     * @param breakTimer A törésvviszaszámláló új értéke.
     */
    @Override
    public void setBreakTimer(int breakTimer) {

    }

    /**
     * Beállítja a szomszédjait a bektonnak.
     *
     * @param neighbours A tekton új szomszédjai.
     */
    @Override
    public void setNeighbours(List<Tecton> neighbours) {

    }

    /**
     * Beállítja a tekton gomafonál kapacítását.
     *
     * @param myceliaCapacity A gombafonál kapacítás új értéke.
     */
    @Override
    public void setMyceliaCapacity(int myceliaCapacity) {

    }

    /**
     * Beállítja a tekton spóráit.
     *
     * @param spores A tekton új spórái.
     */
    @Override
    public void setSpores(Queue<Spore> spores) {

    }

    /**
     * Beállítja a tekon gombatestét a megadott gombatestre.
     *
     * @param mushroomBody A tekton új gombateste.
     */
    @Override
    public void setMushroomBody(MushroomBody mushroomBody) {

    }

    /**
     * Beállítja a tektonon lévő gombafonalakat.
     *
     * @param mycelia A tekton új gombafonalai.
     */
    @Override
    public void setMycelia(Queue<Mycelium> mycelia) {

    }

    /**
     * Beállítja az éppen a tektonon tartozkodó rovarok listáját.
     *
     * @param occupants A tekonon tartózkodó rovarok új listája.
     */
    @Override
    public void setOccupants(List<Insect> occupants) {

    }

    /**
     * Hozzáad egy rovart a tekonon tartózkodó rovarok listájához.
     *
     * @param insect A tektonon újonnan tartózkodó rovar.
     */
    @Override
    public void addOccupant(Insect insect) {

    }

    /**
     * Levesz egy rovart a tekonon tartózkodó rovarok listájáról.
     *
     * @param insect A rovar, amit le szeretnénk szedni a listáról.
     */
    @Override
    public void removeOccupant(Insect insect) {

    }

    /**
     * Hozzáad egy gombafonált a tektonon lévő gombafonalakhoz.
     *
     * @param mycelium Az új gombafonál.
     */
    @Override
    public void addMycelium(Mycelium mycelium) {

    }

    /**
     * Hozzáad egy spórát a tektonhoz.
     *
     * @param spore A tekonra érkező új spóra.
     */
    @Override
    public void addSpore(Spore spore) {

    }

    /**
     * A tektonnak ad egy új szomszédot.
     *
     * @param neighbour A tekton új szomszédja.
     */
    @Override
    public void addNeighbour(Tecton neighbour) {

    }

    /**
     * Visszadja, hogy hoány kör múlva törik el a tekton.
     *
     * @return A tekton törésig hátralévő körök száma.
     */
    @Override
    public int getBreakTimer() {
        return 0;
    }

    /**
     * A tekton szomszédjainak a listáját adja vissza.
     *
     * @return A tekton szomszédjainak listája.
     */
    @Override
    public List<Tecton> getNeighbours() {
        return List.of();
    }

    /**
     * Visszadja a gombafonál kapacítását.
     *
     * @return A tekton gombafonál kapacítása.
     */
    @Override
    public int getMyceliaCapacity() {
        return 0;
    }

    /**
     * A tektonon lévő spórákat visszadja.
     *
     * @return A tektonon lévő spórákat.
     */
    @Override
    public Queue<Spore> getSpores() {
        return null;
    }

    /**
     * Visszaadja a tektonon lévő gombafonalat.
     *
     * @return A tektonon lévő gombatest.
     */
    @Override
    public MushroomBody getMushroomBody() {
        return null;
    }
}
