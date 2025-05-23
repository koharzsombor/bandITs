import java.util.*;
/**
 * Rovar – a gombafonalak mentén mozog (körönként 2 lépést tehet meg), gombafonalakat vág el és spórával táplálkozik.
 * Ha a gombafonalak eltűnnek alóla, egy véletlenszerűen meghatározott tektonra elmenekül.
 * Az osztály többek között nyilvántartja, hogy a rovar milyen spóraeffektus alatt áll.
 */
public class InsectImpl implements Insect{
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
    private int remainingMoves;

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
     * A rovar szakadásainak száma
     */
    private int splitNum = 0;

    /**
     * A rovar jelenlegi tartózkodási helyének setterje.
     * @param location A rovar új tartózkodási helye.
     */
    @Override
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * A rovar jelenlegi tartózkodási helyének getterje.
     * @return A rovar jelenlegi tartózkodási helye.
     */
    @Override
    public Tecton getLocation() {
        return location;
    }

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt setterje.
     * @param maxMoves Az új maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    @Override
    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;
    }

    /**
     * A maximum lépések száma, amit a rovar tehet egy kör alatt getterje.
     * @return A maximum lépések száma, amit a rovar tehet egy kör alatt.
     */
    @Override
    public int getMaxMoves() {
        return maxMoves;
    }

    /**
     * Bealltja a maradék lépéseket a körre
     * @param remainingMoves - Az új maradék lépések száma
     */
    @Override
    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }

    /**
     * A körben lévő maradék lépések számának lekérdezése
     * @return - A körben lévő maradék lépéseket adja vissza
     */
    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * A játék alatt elfogyasztott spórák számának frissitése
     * @param sporesEaten - Az új spóramennyiség amely el volt fogyasztva
     */
    @Override
    public void setSporesEaten(int sporesEaten) {
        this.sporesEaten = sporesEaten;
    }

    /**
     * A játk alatt elfogyasztott spórák számának lekérdezése
     * @return Elfogyasztott spórák száma
     */
    @Override
    public int getSporesEaten() {
        return sporesEaten;
    }

    /**
     * A jelenlegi állapotnak a maradék idejének setterje.
     * @param effectTimer A jelenlegi állapotnak a maradék idejének új értéke.
     */
    @Override
    public void setEffectTimer(int effectTimer) {
        this.effectTimer = effectTimer;
    }

    /**
     * A jelenlegi állapotnak a maradék idejének getterje
     * @return A jelenlegi állapotnak a maradék ideje.
     */
    @Override
    public int getEffectTimer() {
        return effectTimer;
    }

    /**
     * A rovar állapotának setterjeS.
     * @param state A rovar új állapota.
     */
    @Override
    public void setState(InsectState state) {
        this.state = state;
    }

    /**
     * A rovar állapotának getterje.
     * @return A rovar jelenlegi állapota.
     */
    @Override
    public InsectState getState() {
        return state;
    }

    /**
     * A rovar szakadásainak számlálójának a settere.
     * @param splitNum A szakadások új száma.
     */
    @Override
    public void setSplitNum(int splitNum) {
        this.splitNum = splitNum;
    }

    /**
     * A rovar szakadásainak számlálójának a gettere.
     * @return A rovar szakadásainak száma.
     */
    @Override
    public int getSplitNum() {
        return splitNum;
    }

    /**
     * Konstruktor
     * @param location Az a tecton, amelyiken az újonnan létrejött Insect van
     */
    public InsectImpl(Tecton location) {
        setLocation(location);
        location.addOccupant(this);
        setRemainingMoves(getMaxMoves());
        InsectAbstractFactory swingInsectFactory = new SwingInsectFactory();
        swingInsectFactory.onCreateInsect(this);
    }

    /**
     * Elvág egy gombafonalat a jelenlegi tartózkodási helyén.
     */
    @Override
    public void cutMycelium() {
        if(getState() != InsectState.CANNOT_CUT && getRemainingMoves() > 0) {
            getLocation().cutMycelium();
            setRemainingMoves(0);
        }
        ViewRepository.updateObject(location);
        ViewRepository.updateObject(this);
    }

    /**
     * A rovar megesz egy spórát,
     */
    @Override
    public void eatSpore() {
        if(getRemainingMoves() > 0) {
            getLocation().eatSpore(this);
        }
        ViewRepository.updateObject(location);
        ViewRepository.updateObject(this);
    }

    /**
     * A rovar elmozdul a megadott céltektonra, ha van elég lépése a rovarnak.
     * @param target A cél tekton.
     */
    @Override
    public void move(Tecton target) {
        Tecton oldLocation = location;
        if(getRemainingMoves() > 0) {
            target.moveInsect(this);
        }
        ViewRepository.updateObject(oldLocation);
        ViewRepository.updateObject(location);
        ViewRepository.updateObject(this);
    }

    /**
     * A rovar lassú állapotba kerül.
     */
    @Override
    public void beSlow() {
        setState(InsectState.SLOW);
        setEffectTimer(2);
        setMaxMoves(1);
    }

    /**
     * A rovar lassú állapotba kerül.
     */
    @Override
    public void beFast() {
        setState(InsectState.FAST);
        setEffectTimer(2);
        setMaxMoves(3);
    }

    /**
     * A rovar olyan állapotba kerül, amitől nem tudja elvágni a gombafonalakat.
     */
    @Override
    public void preventCut() {
        setState(InsectState.CANNOT_CUT);
        setEffectTimer(2);
    }

    /**
     * A rovar bénult állapotba kerül.
     */
    @Override
    public void beStunned() {
        setState(InsectState.STUN);
        setEffectTimer(1);
        setMaxMoves(0);
    }

    /**
     * A rovar kettészakad
     */
    @Override
    public void split() {
        Insect newInsect = new InsectImpl(getLocation());
        newInsect.setRemainingMoves(0);

        setSplitNum(getSplitNum() + 1);
        String newInsectName = ObjectRegistry.lookupName(this) + "-" + getSplitNum();
        ObjectRegistry.registerObject(newInsectName, newInsect);
        InsectAbstractFactory swingInsectFactory = new SwingInsectFactory();
        swingInsectFactory.onCreateInsect(this);
    }

    /**
     * A rovar elmenekül egy, véletlenszerűen kiválasztott, alkalmas tektonra.
     */
    @Override
    public void runAway() {
        Tecton oldLocation = location;

        Set<Tecton> available = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        Set<Tecton> visited = new HashSet<>();

        queue.add(getLocation());
        visited.add(getLocation());

        //BFS
        while (!queue.isEmpty()) {
            Tecton current = queue.poll();

            if (current.hasMycelium()) {
                available.add(current);
            }

            for (Tecton neighbour : getLocation().getNeighbours()) {
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        if (available.isEmpty()) {
            return;
        }

        Tecton selectedTecton = null;

        int item = new Random().nextInt(available.size());
        int i = 0;
        for(Tecton tecton : available)  {
            if (i == item)
                selectedTecton = tecton;
            i++;
        }
        if (selectedTecton != null) {
            getLocation().removeOccupant(this);
            setLocation(selectedTecton);

            selectedTecton.addOccupant(this);
        }

        ViewRepository.updateObject(oldLocation);
        ViewRepository.updateObject(location);
    }

    /**
     * Ha STUN állapotban van a rovar, meghal (azan eltünik a pályáról, de mint objektum megmarad, pontszámoláshoz)
     */
    @Override
    public void die() {
        if(getState() == InsectState.STUN){
            if(getLocation().getMushroomBody()==null) {
                MushroomBody newMB = new MushroomBodyImpl();
                getLocation().setMushroomBody(newMB);
                newMB.setLocation(getLocation());

                String newMBName = "mb-" + ObjectRegistry.lookupName(getLocation());
                ObjectRegistry.registerObject(newMBName, newMB);
            }
            getLocation().removeOccupant(this);
            setLocation(null);
        }
        ViewRepository.updateObject(location);
    }

    /**
     * A körök kezdetén a jelenlegi állapot visszaszámlálóját csökkenti és ha eléri a 0-át visszatér normál állapotba.
     * Ezenfelül visszaállítja a maradék lépéseket a maximumra.
     */
    @Override
    public void onTurnBegin() {
        if(getEffectTimer() > 0){
            setEffectTimer(getEffectTimer() - 1);
            if(getEffectTimer() == 0){
                setState(InsectState.NORMAL);
                setMaxMoves(2);
            }
        }
        setRemainingMoves(getMaxMoves());
        ViewRepository.updateObject(location);
    }

    /**
     * To string, a kiiráshoz
     * @return az insect tulajdonságainak formázott stringje
     */
    @Override
    public String toString() {
        String output = ObjectRegistry.lookupName(this) + ": Insect\n";
        output += "\tlocation Tecton = " + ObjectRegistry.lookupName(getLocation()) + "\n";
        output += "\tmaxMoves int = " + getMaxMoves() + "\n";
        output += "\tremainingMoves int = " + getRemainingMoves() + "\n";
        output += "\tsporesEaten int = " + getSporesEaten() + "\n";
        output += "\teffectTimer int = " + getEffectTimer() + "\n";
        output += "\tstate InsectState = " + getState().toString() + "\n";
        return output;
    }
}