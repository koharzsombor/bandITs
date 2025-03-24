import java.util.*;

/**
 * A Tektonok közös ősosztálya
 */
public abstract class Tecton implements OnRoundBeginSubscriber {
    /**
     * Az időzitő amely ha elér 0-ra a tekton eltörik
     */
    private int breakTimer = 0;

    /**
     * A tektonnal szomszédos tektonon listája
     */
    private List<Tecton> neighbours = new ArrayList<>();

    /**
     * Maximum ennyi gombafonál lehet az adott tektonon
     */
    private int myceliumCapacity = 0;

    /**
     * Az adott tektonon lévő spórák listája
     */
    private Queue<Spore> spores = new LinkedList<>();

    /**
     * Itt van eltárolva ha az adott tektonon van-e gombatest
     * null - nincs
     */
    private MushroomBody mushroomBody = null;

    /**
     * Az adott tektonon lévő gombafonalak listája
     */
    private Queue<Mycelium> mycelia = new LinkedList<>();

    /**
     * Az adott tektonon lévő bogarak listája
     */
    private List<Insect> occupants = new ArrayList<>();

    /**
     * A tektöntörés időzitő getterje.
     * @return A tektontörés időzítő jelenlegi értéke.
     */
    public int getBreakTimer() {
        return breakTimer;
    }

    /**
     * A tektöntörés időzitő setterje.
     * @param breakTimer Az új idő, ami alatt eltörik a tekton.
     */
    public void setBreakTimer(int breakTimer) {
        this.breakTimer = breakTimer;
    }

    /**
     * A tekton szomszédainak getterje.
     * @return A tekton szomszédai.
     */
    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    /**
     * A tekton szomszédainak setterje.
     * @param neighbours A tekton új szomszédai.
     */
    public void setNeighbours(List<Tecton> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * A maximális gombafonál szám getterje.
     * @return A maximális gombafonál szám.
     */
    public int getMyceliaCapacity() {
        return myceliumCapacity;
    }

    /**
     * A maximális gombafonál szám setterje.
     * @param myceliaCapacity Az új maximális gomafonal szám.
     */
    public void setMyceliaCapacity(int myceliaCapacity) {
        this.myceliumCapacity = myceliaCapacity;
    }

    /**
     * A tektonon lévő spórák getterje.
     * @return A tektonon lévő spórák.
     */
    public Queue<Spore> getSpores() {
        return spores;
    }

    /**
     * A tektonon lévő spórák setterje.
     * @param spores A tekton új spórái.
     */
    public void setSpores(Queue<Spore> spores) {
        this.spores = spores;
    }

    /**
     * A tektonon lévő gombatest getterje.
     * @return A tektonon lévő gombatest.
     */
    public MushroomBody getMushroomBody() {
        return mushroomBody;
    }

    /** A tektonon lévő gombatest setterje.
     * @param mushroomBody A tekton új gombatestje.
     */
    public void setMushroomBody(MushroomBody mushroomBody) {
        this.mushroomBody = mushroomBody;
    }

    /** A tektonon lévő gombafonalak getterje.
     * @return A tektonon lévő gombafonalak.
     */
    public Queue<Mycelium> getMycelia() {
        return mycelia;
    }

    /** A tektonon lévő gombafonalak setterje.
     * @param mycelia A tekton új gombafonalai.
     */
    public void setMycelia(Queue<Mycelium> mycelia) {
        this.mycelia = mycelia;
    }

    /**
     * A függvény megadja, hogy milyen messze van egy cél tekton a jelenlegi tektontól.
     * @param tecton A cél tekton.
     * @return A távolság a jelen és a cél tekton között.
     */
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

            for (Tecton neighbour : current.neighbours) {
                distances.computeIfAbsent(neighbour, distance -> {
                    queue.add(neighbour);
                    return currentDistance + 1;
                });
            }
        }

        return -1;
    }

    /**
     * A tektonok összegsége, aminek fonalai nincsenek összeköttetésben gombatestel, ezért el fognak pusztulni.
     */
    private Set<Tecton> notSustained = new HashSet<>();

    /**
     * Azok a szomszédok összessége, amelyen van gombafonál vagy gombatest.
     * @return A szomszédos tektonok, amin van gombafonál vagy gombatest.
     */
    private List<Tecton> neighboursWithMycelia() {
        return neighbours.stream().filter(t -> (t.hasMycelium()) || (t.mushroomBody != null)).toList();
    }

    /**
     * A függvény megnézi, hogy a szomszédos tektonok és velük gombafonállal össsze
     * kötött tektonok még összekötteésbe állnak-e gombatestel.
     */
    public void checkNeighbourMyceliaSustain() {
        notSustained.clear();

        neighbours.forEach(Tecton::myceliaCheckSustain);

        for (Tecton tecton : notSustained) {
            for (Mycelium m : tecton.getMycelia()) {
                tecton.mycelia.remove(m);
                m.delete();
            }
        }
    }

    /**
     * A függvény megnézi, hogy a tekton és velük gombafonállal össsze
     * kötött tektonok még összekötteésbe állnak-e gombatestel.
     */
    private void myceliaCheckSustain() {
        Set<Tecton> connected = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        Set<Tecton> visited = new HashSet<>();

        boolean hasMushroomBody = false;

        queue.add(this);
        visited.add(this);

        //BFS
        while (!queue.isEmpty()) {
            Tecton current = queue.poll();

            connected.add(current);
            if (current.mushroomBody != null)
                hasMushroomBody = true;

            for (Tecton neighbour : neighboursWithMycelia()) {
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        if (!hasMushroomBody) {
            notSustained.addAll(connected);
        }
    }

    /**
     * A tektonon lévő rovarok listájának getterje.
     * @return A tektonon lévő rovarok.
     */
    public List<Insect> getOccupants() {
        return occupants;
    }

    /**
     * A tektonon lévő rovarok listájának setterje
     * @param occupants A tektonra kerülő rovarok.
     */
    public void setOccupants(List<Insect> occupants) {
        this.occupants = occupants;
    }

    /**
     * Hozzáad egy rovart a tektonhoz.
     * @param insect A tekton új rovarja.
     */
    public void addOccupant(Insect insect) {
        occupants.add(insect);
    }

    /**
     * Levesz egy rovart a tektonról.
     * @param insect A rovart, amit le szeretnénk venni.
     */
    public void removeOccupant(Insect insect) {
        occupants.remove(insect);
    }

    /**
     * Egy rovart a jelenelgi tektonra mozgartja.
     * @param insect A mozgatott rovar.
     * @param insectLocation A rovar eredeti helye.
     */
    public void moveInsect(Insect insect, Tecton insectLocation) {
        if(Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=distance(%s)=> %s %n \t<=distance: int =%s %n", Main.objectNames.get(this), Main.objectNames.get(insectLocation), Main.objectNames.get(insectLocation));
        }
        int distance = insectLocation.distance(this);
        if(distance==1 && this.hasMycelium()){
            if(Main.printTrace) {
                System.out.printf("\t=removeOccupant(%s)=> %s %n", Main.objectNames.get(insect), Main.objectNames.get(insectLocation));
                System.out.printf("\t=setLocation(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(insect));
            }
            insectLocation.removeOccupant(insect);
            this.addOccupant(insect);
            insect.setLocation(this);
            insect.setRemainingMoves(insect.getRemainingMoves()-1);
        }
    }

    /**
     * Megadja, hogy van-e legalább 1 gombafonál a tektonon.
     * @return Igaz ha van gombafonál, hamis ha nincs.
     */
    public boolean hasMycelium() {
        return !mycelia.isEmpty();
    }

    /**
     * Hozzáad egy gombafonalat a tektonhoz.
     * @param mycelium A tekton új gombafonala.
     */
    public void addMycelium(Mycelium mycelium) {
        mycelia.offer(mycelium);
    }

    /**
     * A fonal elvágására használt metódus
     */
    public void cutMycelium() {
        boolean originalPrintTrace = Main.printTrace;

        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=cut()=> %s", Main.objectNames.get(mycelia.element()));
        }

        mycelia.poll().cut();
        if (mycelia.isEmpty() || !occupants.isEmpty()) {
            List<Insect> temp = new ArrayList<>();
            for (Insect I : occupants) {
                temp.add(I);
            }
            for (Insect I : temp) {
                if (Main.printTrace) {
                    System.out.printf("\trunAway()=> %s %n", Main.objectNames.get(I));
                }
                Main.printTrace = false;
                I.runAway();
                Main.printTrace = originalPrintTrace;
            }
        }
    }


    /**
     * Hozzáad egy spórát a tektonhoz,
     * @param spore Az új spóra.
     */
    public void addSpore(Spore spore) {
        getSpores().offer(spore);
    }

    /**
     * Hozzáad egyszerre több spórát a tektonhoz.
     * @param newSpores Az új spórák listája.
     */
    public void transferSpores(List<Spore> newSpores) {
        spores.addAll(newSpores);
    }

    /**
     * Egy új szomszédot ad a tektonnak.
     * @param tecton Az új szomszéd tekton.
     */
    public void addNeighbour(Tecton tecton) {
        neighbours.add(tecton);
    }

    /**
     * A tektonon lévő spórákból az elsőt a kapott rovar megeszi, majd a megevett spórát kitörli.
     * @param insect A rovar, ami a spórát megeszi.
     */
    public void eatSpore(Insect insect) {
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=eatSpore(%s)=> %s %n", Main.objectNames.get(insect), Main.objectNames.get(spores.peek()));
        }
        Spore spore = spores.poll();

        if (spore != null) {
            spore.eatSpore(insect);
            Main.mockDeletion(spore);
        }
    }

    /**
     * Eldönti, hogy az adott gombafonál nőhet-e ezen a tektonon.
     * @param MyceliumGrowthEvaluator A kommunikációban segítő visitor.
     * @param mycelium Az adott gombafonál, amelyikről a tekton eldönti, hogy ránőhet-e.
     */
    public abstract void accept(MyceliumGrowthEvaluator MyceliumGrowthEvaluator, Mycelium mycelium);

    /**
     * Eldönti, hogy az adott gombatest nőhet-e ezen a tektonon.
     * @param mushroomBodyGrowthEvaluator A kommunikációban segítő visitor.
     * @param mushroomBody Az adott gombatest, amelyikről a tekton eldönti, hogy ránőhet-e.
     */
    public abstract void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody);
}
