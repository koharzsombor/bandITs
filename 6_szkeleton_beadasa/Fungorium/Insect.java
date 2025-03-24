import java.util.*;

/**
 * Rovar – a gombafonalak mentén mozog (körönként 2 lépést tehet meg), gombafonalakat vág el és spórával táplálkozik.
 * Ha a gombafonalak eltűnnek alóla, egy véletlenszerűen meghatározott tektonra elmenekül.
 * Az osztály többek között nyilvántartja, hogy a rovar milyen spóraeffektus alatt áll.
 */
public class Insect implements OnTurnBeginSubscriber {
    /**
     * A rovar jelenlegi tartózkodási helye.
     */
    private Tecton location;

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    private int maxMoves = 2;

    /**
     * A körben lévő maradék lépéseknek a száma.
     */
    private int remainingMoves = 0;

    /**
     * A játék alatt elfogyasztott spórák száma.
     */
    private int sporesEaten = 0;

    /**
     * A rovar jelenlegi állapotának maradék ideje körökben.
     */
    private int effectTimer = 0;

    /**
     * A rovar jelenlegi állapota.
     */
    private InsectState state = InsectState.NORMAL;

    /**
     * A rovar jelenlegi tartózkodási helyének getterje.
     * @return A rovar jelenlegi tartózkodási helye.
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * A rovar jelenlegi tartózkodási helyének setterje.
     * @param location A rovar új tartózkodási helye.
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt getterje.
     * @return A maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    public int getMaxMoves() {
        return maxMoves;
    }

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt setterje.
     * @param maxMoves Az új maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;
    }

    /**
     * A körben lévő maradék lépések számának lekérdezése
     * @return - A körben lévő maradék lépéseket adja vissza
     */
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Bealltja a maradék lépéseket a körre
     * @param remainingMoves - Az új maradék lépések száma
     */
    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }

    /**
     * A játk alatt elfogyasztott spórák számának lekérdezése
     * @return
     */
    public int getSporesEaten() {
        return sporesEaten;
    }

    /**
     * A játék alatt elfogyasztott spórák számának frissitése
     * @param sporesEaten - Az új spóramennyiség amely el volt fogyasztva
     */
    public void setSporesEaten(int sporesEaten) {
        this.sporesEaten = sporesEaten;
    }

    /**
     * A jelenlegi állapotnak a maradék idejének getterje
     * @return A jelenlegi állapotnak a maradék ideje.
     */
    public int getEffectTimer() {
        return effectTimer;
    }

    /**
     * A jelenlegi állapotnak a maradék idejének setterje.
     * @param effectTimer A jelenlegi állapotnak a maradék idejének új értéke.
     */
    public void setEffectTimer(int effectTimer) {
        this.effectTimer = effectTimer;
    }

    /**
     * A rovar állapotának getterje.
     * @return A rovar jelenlegi állapota.
     */
    public InsectState getState() {
        return state;
    }

    /**
     * A rovar állapotának setterjeS.
     * @param state A rovar új állapota.
     */
    public void setState(InsectState state) {
        this.state = state;
    }

    /**
     * Elvág egy gombafonalat a jelenlegi tartózkodási helyén.
     */
    public void cutMycelium() {
        if(getRemainingMoves() > 0 && getState() != InsectState.CANNOT_CUT) {
            location.cutMycelium();
            setRemainingMoves(getRemainingMoves()-1);
        }
    }

    /**
     * A rovar megeszi azt a spórát,
     */
    public void eatSpore() {
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=eatSpore(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getLocation()));
        }

        getLocation().eatSpore(this);
    }

    /**
     * A rovar elmozdul a megadott céltektonra, ha van elég lépése a rovarnak.
     * @param target A cél tekton.
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
     * A rovar lassú állapotba kerül.
     */
    public void beSlow() {
        setState(InsectState.SLOW);
        setMaxMoves(1);
    }

    /**
     * A rovar gyors állapotba kerül.
     */
    public void beFast() {
        setState(InsectState.FAST);
        setMaxMoves(3);
    }

    /**
     * A rovar olyan állapotba kerül, amitől nem tudja elvágni a gombafonalakat.
     */
    public void preventCut() {
        setState(InsectState.CANNOT_CUT);
    }

    /**
     * A rovar bénult állapotba kerül.
     */
    public void beStunned() {
        setState(InsectState.STUN);
        setMaxMoves(0);
        setMaxMoves(0);
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

            while (selectedTecton == null) {
                available.forEach(t -> System.out.println(Main.objectNames.get(t)));

                String input = Main.selectScanner.nextLine();

                for (Tecton tecton : available) {
                    if (Main.objectNames.get(tecton).toLowerCase().charAt(0) == input.toLowerCase().charAt(0)) {
                        selectedTecton = tecton;
                        break;
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

    /**
     * A körök kezdetén a jelenlegi állapot visszaszámlálóját csökkenti és ha eléri a 0-át visszatér normál állapotba.
     * Ezenfelül visszaállítja a maradék lépéseket a maximumra.
     */
    @Override
    public void onTurnBegin() {
        if (effectTimer > 0) {
            effectTimer--;

            if (effectTimer <= 0) {
                setState(InsectState.NORMAL);
                setMaxMoves(2);
            }
        }

        remainingMoves = maxMoves;
    }
}
