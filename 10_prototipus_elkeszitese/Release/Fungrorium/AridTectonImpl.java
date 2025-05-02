import java.util.ArrayList;
import java.util.Random;

public class AridTectonImpl extends FertileTectonImpl {

    private int abosrbCountdown;

    int MINNUMB = 2;
    int MAXNUMB = 2;
    Random rand = new Random();

    /**
     * AridTecton konstruktora
     */
    AridTectonImpl() {
        setMyceliaCapacity(1);

        setBreakTimer(rand.nextInt(MAXNUMB - MINNUMB + 1) + MINNUMB);
    }

    /**
     * @param myceliumGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mycelium                A gombafonál, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    @Override
    public void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium) {
        if (getMycelia().size() >= getMyceliaCapacity()) {
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        int sporeCount = getSpores().size();
        mycelium.grow(sporeCount);
        abosrbCountdown = 5;
    }

    // accept - Mushroombody ugyanaz mint FertileTecton

    /**
     * Ugyan az mint FertileTecton, tektontorest figyel
     * Plusz munkaja az absorbCountdown csokkentese, es fonalak torlese ha eleri a 0-at
     */
    @Override
    public void onRoundBegin() {
        if (abosrbCountdown > 0) {
            abosrbCountdown--;
            if (abosrbCountdown <= 0) {
                Mycelium mycelium = this.getMycelia().poll();
                if (mycelium != null) {
                    mycelium.delete();
                }
            }
        }

        setBreakTimer(getBreakTimer() - 1);

        if (getBreakTimer() <= 0) {

            while(!getMycelia().isEmpty()) {
                Mycelium mycelium = getMycelia().poll();
                assert mycelium != null;
                mycelium.cutImmediate();
            }

            ArrayList<Insect> temp =new ArrayList<Insect>(getOccupants());
            for (Insect insect : temp) {
                insect.runAway();
            }

            this.breakCounter++;

            getSpores().clear();

            FertileTectonImpl newFertileTecton = new FertileTectonImpl();
            newFertileTecton.addNeighbour(this);
            this.addNeighbour(newFertileTecton);
            String newFTname = ObjectRegistry.lookupName(this) + "-" + this.breakCounter;
            ObjectRegistry.registerObject(newFTname, newFertileTecton);

            setBreakTimer(rand.nextInt(MAXNUMB - MINNUMB + 1) + MINNUMB);
        }
    }

    // sustaining - ugyan az mint FertileTectonban

    /**
     * To string, a kiiráshoz
     * @return az tecton tulajdonságainak formázott stringje
     */
    @Override
    public String toString() {
        String output = ObjectRegistry.lookupName(this) + ": AridTecton\n";
        output += "\tbreakTimer int = " + getBreakTimer() + "\n";
        output += "\tneighbours List<Tecton> = {" + "\n";
                for(Tecton tecton : getNeighbours()) {
                    output += "\t" + ObjectRegistry.lookupName(tecton) + "\n";
                }
                output += "\t}\n";
        output +="\tmyceliumCapacity int = " + getMyceliaCapacity() + "\n";
        output += "\tspores Queue<Spore> = {" + "\n";
                for(Spore spore : getSpores()) {
                    output += "\t" + ObjectRegistry.lookupName(spore) + "\n";
                }
                output += "\t}\n";
        output += "\tmushroomBody MushroomBody = " + ObjectRegistry.lookupName(getMushroomBody()) + "\n";
        output += "\tmycelia Queue<Mycelium> = {" + "\n";
                for(Mycelium mycelium : getMycelia()) {
                    output += "\t" + ObjectRegistry.lookupName(mycelium) + "\n";
                }
                output += "\t}\n";
        output += "\toccupants List<Insect> = {" + "\n";
                for(Insect insect : getOccupants()) {
                    output += "\t" + ObjectRegistry.lookupName(insect) + "\n";
                }
                output += "\t}\n";
        return output;
    }

    /**
     * @param mycelium Az új gombafonál hozzaadasa a listahoz es az abosrbCountdown elinditasa
     */
    @Override
    public void addMycelium(Mycelium mycelium){
        super.addMycelium(mycelium);
        abosrbCountdown = 5;
    }
}
