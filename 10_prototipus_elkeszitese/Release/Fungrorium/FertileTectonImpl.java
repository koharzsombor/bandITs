import java.util.ArrayList;
import java.util.Random;

public class FertileTectonImpl extends TectonImpl {

    Random rand = new Random();

    int MINNUMB = 2;
    int MAXNUMB = 2;

    /**
     * FertileTecton konstruktora
     */
    public FertileTectonImpl() {
        setMyceliaCapacity(1);

        setBreakTimer(rand.nextInt(MAXNUMB - MINNUMB + 1) + MINNUMB);
    }

    /**
     * @param myceliumGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mycelium                A gombafonál, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    @Override
    public void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium) {
        if (getMycelia().size() >= getMyceliaCapacity() || neighboursWithMycelia().isEmpty()) {
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        int sporeCount = getSpores().size();
        mycelium.grow(sporeCount);
    }

    /**
     * @param mushroomBodyGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mushroomBody                A gombatest, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        if (getSpores().size() < 3 || getMushroomBody() != null || getMycelia().isEmpty()) {
            mushroomBody.delete();
            return;
        }

        setMushroomBody(mushroomBody);
        mushroomBody.grow(getSpores().size());
    }

    /**
     * Tektontorest kezeli
     */
    @Override
    public void onRoundBegin() {
        setBreakTimer(getBreakTimer() - 1);

        if (getBreakTimer() <= 0) {
            while(!getMycelia().isEmpty()) {
                Mycelium mycelium = getMycelia().poll();
                assert mycelium != null;
                mycelium.cutImmediate();
            }

            ArrayList<Insect> temp =new ArrayList<>(getOccupants());
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

    /**
     * @return Ha van rajta gombatest true, ha nincs false
     */
    public boolean sustaining(){
        return getMushroomBody() != null;
    }

    /**
     * To string, a kiiráshoz
     * @return az tecton tulajdonságainak formázott stringje
     */
    @Override
    public String toString() {
        String output = ObjectRegistry.lookupName(this) + ": FertileTecton\n";
        output += "\tbreakTimer int = " + getBreakTimer() + "\n";
        output += "\tneighbours List<Tecton> = {" + "\n";
        for(Tecton tecton : getNeighbours()) {
            output += "\t\t" + ObjectRegistry.lookupName(tecton) + "\n";
        }
        output += "\t}\n";
        output +="\tmyceliumCapacity int = " + getMyceliaCapacity() + "\n";
        output += "\tspores Queue<Spore> = {" + "\n";
        for(Spore spore : getSpores()) {
            output += "\t\t" + ObjectRegistry.lookupName(spore) + "\n";
        }
        output += "\t}\n";
        output += "\tmushroomBody MushroomBody = " + ObjectRegistry.lookupName(getMushroomBody()) + "\n";
        output += "\tmycelia Queue<Mycelium> = {" + "\n";
        for(Mycelium mycelium : getMycelia()) {
            output += "\t\t" + ObjectRegistry.lookupName(mycelium) + "\n";
        }
        output += "\t}\n";
        output += "\toccupants List<Insect> = {" + "\n";
        for(Insect insect : getOccupants()) {
            output += "\t\t" + ObjectRegistry.lookupName(insect) + "\n";
        }
        output += "\t}\n";
        return output;
    }
}
