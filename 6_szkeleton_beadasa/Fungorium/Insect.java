import java.util.*;

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
        if(getRemainingMoves()>0) {
            location.cutMycelium();
            setRemainingMoves(getRemainingMoves()-1);
        }
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

        if(getRemainingMoves()>0) {
            target.moveInsect(this, location);
        }
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
     * A rovar elfut egy véletlenszerű tektonra, ahol van gombafonál.
     */
    public void runAway() {
        Set<Tecton> available = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        Set<Tecton> visited = new HashSet<>();

        queue.add(getLocation());
        visited.add(location);

        //BFS
        while (!queue.isEmpty()) {
            Tecton current = queue.poll();

            boolean hasMycelium;
            if (Main.printTrace) {
                System.out.println(Main.objectNames.get(this));
                System.out.printf("\t=hasMycelium()=> %s %n", Main.objectNames.get(current));
                System.out.printf("\t<=hasMycelium= %s %n", Main.objectNames.get(current));
            }

            hasMycelium = current.hasMycelium();
            if (hasMycelium) {
                available.add(current);
            }

            for (Tecton neighbour : location.getNeighbours()) {
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        if (available.isEmpty())
            return;

        Tecton selectedTecton = null;
        if (Main.printTrace) {
            System.out.println("\t=selectedTecton=> input");
            System.out.println("Kérem adja meg, hogy melyik tektonra meneküljön a rovar!");

            try(Scanner selectScanner = new Scanner(System.in)) {
                while (selectedTecton == null) {
                    available.forEach(t -> System.out.println(Main.objectNames.get(t)));

                    String input = selectScanner.nextLine();

                    for (Tecton tecton : available) {
                        if (Main.objectNames.get(tecton).toLowerCase().charAt(0) == input.toLowerCase().charAt(0)) {
                            selectedTecton = tecton;
                        }
                    }
                }
            }

            System.out.println("\t<=selectedTecton= input");
        }
        else {
            int item = new Random().nextInt(available.size());
            int i = 0;
            for(Tecton tecton : available)  {
                if (i == item)
                    selectedTecton = tecton;
                i++;
            }
        }
        if (selectedTecton != null) {
            setLocation(selectedTecton);

            if (Main.printTrace)
                System.out.printf("\t=addOccupant(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(selectedTecton));

            selectedTecton.addOccupant(this);
        }
    }
}
