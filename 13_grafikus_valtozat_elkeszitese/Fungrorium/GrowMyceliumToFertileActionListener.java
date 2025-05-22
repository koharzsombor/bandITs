import java.awt.event.ActionListener;
import java.util.Random;

/**
 * GrowMycelium Fertile Tectonra
 */
public class GrowMyceliumToFertileActionListener implements ActionListener {

    /**
     * Random generator
     */
    private Random random;

    /**
     * FertileTecton, amihez tartozik
     */
    FertileTectonImpl tecton;

    /**
     * Konstruktor
     * @param tecton FertileTecton
     */
    GrowMyceliumToFertileActionListener(FertileTectonImpl tecton) {
        this.tecton = tecton;
        random = new Random();
    }

    /**
     * Létrehozza a Myceliumot
     * @param e the event to be processed
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        //Mycelium mycelium = new MyceliumImpl(tecton);
        String myceliumType = random.nextInt(100) > 85 ? "carnivorousmycelium" : "mycelium";
        GrowthControllerImpl.instance.growMycelium(myceliumType, ObjectRegistry.lookupName(tecton) + "-mycelium", tecton, null);
    }
}
