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
        location.cutMycelium();
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

        target.moveInsect(this, location);
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
     *
     */
    public void runAway() {
        boolean found=false;
        Tecton chosenTecton = getLocation();
        while(found==false) {
            //Ide fog jönni a random tectonválasztás
            //TectonBreak eset
            if(Main.objectNames.get(location.getNeighbours().getFirst().getNeighbours().getFirst()).equals(Main.objectNames.get(getLocation()))) {
                if (Main.printTrace) {
                    System.out.println(Main.objectNames.get(this));
                    System.out.printf("\t=hasMycelium()=> %s %n <=foundTecton: bool= %s %n", Main.objectNames.get(location.getNeighbours().getFirst()), Main.objectNames.get(this));
                }
                chosenTecton = location.getNeighbours().getFirst();
                found = chosenTecton.hasMycelium();
            }
            //runAway test eset
            else {
                if (Main.printTrace) {
                    System.out.println(Main.objectNames.get(this));
                    System.out.printf("\t=hasMycelium()=> %s %n <=foundTecton: bool= %s %n", Main.objectNames.get(location.getNeighbours().getFirst()), Main.objectNames.get(this));
                    System.out.printf("\t=hasMycelium()=> %s %n <=foundTecton: bool= %s %n", Main.objectNames.get(location.getNeighbours().getFirst().getNeighbours().getFirst()), Main.objectNames.get(this));
                }
                chosenTecton = location.getNeighbours().getFirst();
                found = chosenTecton.hasMycelium();
                chosenTecton = chosenTecton.getNeighbours().getFirst();
                found = chosenTecton.hasMycelium();
            }
        }
        if(Main.printTrace) {
            System.out.printf("\t=addOccupant(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(chosenTecton));
            System.out.printf("\t=removeOccupant(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(location));
        }
        chosenTecton.addOccupant(this);
        location.removeOccupant(this);
        this.setLocation(chosenTecton);

    }
}
