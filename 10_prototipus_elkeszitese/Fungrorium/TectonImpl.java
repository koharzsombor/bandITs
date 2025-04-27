import java.util.*;

/**
 * A tekton implementációja.
 */
public abstract class TectonImpl implements Tecton {
    /**
     * Az időzitő amely ha elér 0-ra a tekton eltörik.
     */
    private int breakTimer;

    protected int breakCounter = 0;
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
    private static final Set<Tecton> notSustained = new HashSet<>();

    /**
     * Meghatározza a távolságot a jelen tekton és a megadott tekton között.
     *
     * @param tecton A megadott tekton, amitől a távolságot számoljuk.
     * @return A két tekton közti távolság.
     */
    @Override
    public int distance(Tecton tecton) {

        Map<Tecton, Integer> distances = new HashMap<>();
        Queue<Tecton> queue = new LinkedList<>();

        distances.put(this, 0);
        queue.add(this);

        //BFS
        while (!queue.isEmpty()) {
            Tecton current = queue.poll();
            int currentDistance = distances.get(current);

            if (current == tecton)
                return currentDistance;

            for (Tecton neighbour : current.getNeighbours()) {
                distances.computeIfAbsent(neighbour, distance -> {
                    queue.add(neighbour);
                    return currentDistance + 1;
                });
            }
        }

        return -1;
    }

    /**
     * Ellenörzi, hogy a szomszédos gombafonállal rendelkező tektonok a gombafonalakat valami
     * életben tartja-e. Ha nem tartja életben semmi akkor kitörli őket.
     *
     * @return
     */
    @Override
    public void checkNeighbourMyceliaSustain() {
        if (!mycelia.isEmpty())
            return;

        notSustained.clear();
        neighboursWithMycelia().forEach(Tecton::myceliaCheckSustain);

        for (Tecton tecton : notSustained) {
            for (Mycelium m : tecton.getMycelia()) {
                tecton.getMycelia().remove(m);
                m.delete();
            }
        }
    }

    /**
     * A függvény megnézi, hogy a tekton és velük gombafonállal össsze
     * kötött tektonok még összekötteésbe állnak-e gombatestel vagy SustainingTectonnal.
     */
    public void myceliaCheckSustain () {
        Set<Tecton> connected = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        Set<Tecton> visited = new HashSet<>();

        boolean isSustaining = false;

        queue.add(this);
        visited.add(this);

        //BFS
        while (!queue.isEmpty()) {
            Tecton current = queue.poll();

            connected.add(current);
            if (current.sustaining())
                isSustaining = true;

            for (Tecton neighbour : neighboursWithMycelia()) {
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        if (!isSustaining) {
            notSustained.addAll(connected);
        }
    }


    /**
    * Azok a szomszédok összessége, amelyen van gombafonál vagy gombatest.
    * @return A szomszédos tektonok, amin van gombafonál vagy gombatest.
     */
    protected List<Tecton> neighboursWithMycelia() {
        return neighbours.stream().filter(t -> (t.hasMycelium()) || (t.getMushroomBody() != null)).toList();
    }

    /**
     * Hozzáad egyszerre több spórát a tektonhoz.
     *
     * @param newSpores A spórák listája, amiket hozzáadunk az új tektonhoz.
     */
    @Override
    public void transferSpores(List<Spore> newSpores) {
        this.spores.addAll(newSpores);
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
        List<Insect> originalOccupants = new LinkedList<>(occupants);

        for (Insect insect : originalOccupants) {
            insect.die();
        }
    }

    /**
     * A megadott rovar megeszi a tektonon lévő, legrégebben ott tartózkodó spórát.
     *
     * @param insect A spórát elfogyasztó rovar.
     */
    @Override
    public void eatSpore(Insect insect) {
        if (!spores.isEmpty()) {
            spores.poll().eatSpore(insect);
            insect.setRemainingMoves(0);
            insect.setSporesEaten(insect.getSporesEaten() + 1);
        }
    }

    /**
     * A tektonon lévő gombafonál elrágása.
     */
    @Override
    public void cutMycelium() {
        mycelia.poll().cutWithDelay();
    }

    /**
     * Ha tud a rovar a jelen tectonra mozogni, akkor megcsinálja ezt a műveletet.
     *
     * @param insect A rovar, akit mozgat a függvény.
     */
    @Override
    public void moveInsect(Insect insect) {
        Tecton insectLocation = insect.getLocation();
        int distance = insectLocation.distance(this);

        if (distance==1 && this.hasMycelium()){
            insectLocation.removeOccupant(insect);
            this.addOccupant(insect);
            insect.setLocation(this);
            int newRemainingMoves = insect.getRemainingMoves()-1;
            insect.setRemainingMoves(newRemainingMoves);
        }
    }

    /**
     * Beállítja a tekton törésvisszaszámlálóját.
     *
     * @param breakTimer A törésvviszaszámláló új értéke.
     */
    @Override
    public void setBreakTimer(int breakTimer) {
        this.breakTimer = breakTimer;
    }

    /**
     * Beállítja a szomszédjait a bektonnak.
     *
     * @param neighbours A tekton új szomszédjai.
     */
    @Override
    public void setNeighbours(List<Tecton> neighbours) {
        this.neighbours.clear();
        this.neighbours.addAll(neighbours);
    }

    /**
     * Beállítja a tekton gomafonál kapacítását.
     *
     * @param myceliaCapacity A gombafonál kapacítás új értéke.
     */
    @Override
    public void setMyceliaCapacity(int myceliaCapacity) {
        this.myceliaCapacity = myceliaCapacity;
    }

    /**
     * Beállítja a tekton spóráit.
     *
     * @param spores A tekton új spórái.
     */
    @Override
    public void setSpores(Queue<Spore> spores) {
        this.spores.clear();
        this.spores.addAll(spores);
    }

    /**
     * Beállítja a tekon gombatestét a megadott gombatestre.
     *
     * @param mushroomBody A tekton új gombateste.
     */
    @Override
    public void setMushroomBody(MushroomBody mushroomBody) {
        this.mushroomBody = mushroomBody;
    }

    /**
     * Beállítja a tektonon lévő gombafonalakat.
     *
     * @param mycelia A tekton új gombafonalai.
     */
    @Override
    public void setMycelia(Queue<Mycelium> mycelia) {
        this.mycelia.clear();
        this.mycelia.addAll(mycelia);
    }

    /**
     * Beállítja az éppen a tektonon tartozkodó rovarok listáját.
     *
     * @param occupants A tekonon tartózkodó rovarok új listája.
     */
    @Override
    public void setOccupants(List<Insect> occupants) {
        this.occupants.clear();
        this.occupants.addAll(occupants);
    }

    /**
     * Hozzáad egy rovart a tekonon tartózkodó rovarok listájához.
     *
     * @param insect A tektonon újonnan tartózkodó rovar.
     */
    @Override
    public void addOccupant(Insect insect) {
        this.occupants.add(insect);
    }

    /**
     * Levesz egy rovart a tekonon tartózkodó rovarok listájáról.
     *
     * @param insect A rovar, amit le szeretnénk szedni a listáról.
     */
    @Override
    public void removeOccupant(Insect insect) {
        this.occupants.remove(insect);
    }

    /**
     * Hozzáad egy gombafonált a tektonon lévő gombafonalakhoz.
     *
     * @param mycelium Az új gombafonál.
     */
    @Override
    public void addMycelium(Mycelium mycelium) {
        this.mycelia.add(mycelium);
    }

    /**
     * Hozzáad egy spórát a tektonhoz.
     *
     * @param spore A tekonra érkező új spóra.
     */
    @Override
    public void addSpore(Spore spore) {
        this.spores.add(spore);
    }

    /**
     * A tektonnak ad egy új szomszédot.
     *
     * @param neighbour A tekton új szomszédja.
     */
    @Override
    public void addNeighbour(Tecton neighbour) {
        this.neighbours.add(neighbour);
    }

    /**
     * Visszadja, hogy hoány kör múlva törik el a tekton.
     *
     * @return A tekton törésig hátralévő körök száma.
     */
    @Override
    public int getBreakTimer() {
        return breakTimer;
    }

    /**
     * A tekton szomszédjainak a listáját adja vissza.
     *
     * @return A tekton szomszédjainak listája.
     */
    @Override
    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    /**
     * Visszaadja a szomszédos tektonok listáját
     * @return A szomszédos tektonok listája
     */
    @Override
    public List<TectonView> getNeighboursViews() {
        return new ArrayList<>(neighbours);

    }

    /**
     * Visszadja a gombafonál kapacítását.
     *
     * @return A tekton gombafonál kapacítása.
     */
    @Override
    public int getMyceliaCapacity() {
        return myceliaCapacity;
    }

    /**
     * A tektonon lévő spórákat visszadja.
     *
     * @return A tektonon lévő spórákat.
     */
    @Override
    public Queue<Spore> getSpores() {
        return spores;
    }

    /**
     * Visszaadja a tektonon lévő gombatestet.
     *
     * @return A tektonon lévő gombatest.
     */
    @Override
    public MushroomBody getMushroomBody() {
        return mushroomBody;
    }

    /**
     * Visszaadja a tektonon lévő fonalat.
     * @return A tektonon lévő fonalak listája
     */
    @Override
    public Queue<Mycelium> getMycelia() {
        return mycelia;
    }

    /**
     * True ha van legalább egy fonál
     * False ha nincs egy fonál sem a tektonon
     * @return True vagy false a fentiek szerint
     */
    @Override
    public boolean hasMycelium() {
        for (Mycelium mycelium : mycelia) {
            if (!mycelium.isGrowing())
                return true;
        }

        return false;
    }

    @Override
    public List<Insect> getOccupants() {
        return occupants;
    }
}
