import javax.naming.NamingEnumeration;
import java.util.ArrayList;
import java.util.Random;

public class SustainingTectonImpl extends FertileTectonImpl {

    Random rand = new Random();

    int MINNUMB = 2;
    int MAXNUMB = 2;

    /**
     * SustainingTecton konstruktor
     */
    SustainingTectonImpl() {
        setMyceliaCapacity(1);

        setBreakTimer(rand.nextInt(MAXNUMB - MINNUMB + 1) + MINNUMB);
    }

    // accept - Mycelium ugyan az mint FertileTecton

    // accept - MushroomBody ugyan az mint FertileTecton

    // onRoundBegin - ugyan az mint FertileTecton

    /**
     * Mindig igaz mivel a SustainingTecton ugy viselkedik mintha mindig lenne rajta MushroomBody
     * @return mindig true
     */
    @Override
    public boolean sustaining(){
        return true;
    }

    /**
     * To string, a kiiráshoz
     * @return az tecton tulajdonságainak formázott stringje
     */
    @Override
    public String toString() {
        String output = ObjectRegistry.lookupName(this) + ": SustainingTecton\n";
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
