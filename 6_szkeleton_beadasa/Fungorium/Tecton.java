import java.util.*;

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
    private MushroomBody mushroomBody = null;

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
     * @param insect
     * @param insectLocation
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
     * @return
     */
    public boolean hasMycelium() {
        return !mycelia.isEmpty();
    }

    /**
     * @param mycelium
     */
    public void addMycelium(Mycelium mycelium) {
        mycelia.offer(mycelium);
    }

    public void cutMycelium() {
        boolean originalPrintTrace = Main.printTrace;

        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=cut()=> %s %n", Main.objectNames.get(mycelia.element()));
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
     * @param spore
     */
    public void addSpore(Spore spore) {
        getSpores().offer(spore);
    }

    public void transferSpores(List<Spore> newSpores) {
        spores.addAll(newSpores);
    }

    /**
     * @param tecton
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
     * @param mushroomBodyGrowthEvaluator
     * @param mushroomBody
     */
    public abstract void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody);
}
