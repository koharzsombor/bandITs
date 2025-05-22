import java.awt.event.ActionListener;
import java.util.Random;

/**
 * GrowMycelium Fertile Tectonra
 */
public class GrowMyceliumToSemiFertileActionListener implements ActionListener {

    /**
     * FertileTecton, amihez tartozik
     */
    SemiFertileTectonImpl tecton;

    /**
     * Random generator
     */
    private Random random;

    /**
     * Konstruktor
     * @param tecton FertileTecton
     */
    GrowMyceliumToSemiFertileActionListener(SemiFertileTectonImpl tecton) {
        this.tecton = tecton;
        random = new Random();
    }

    /**
     * Létrehozza a Myceliumot
     * @param e the event to be processed
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String myceliumType = random.nextInt(100) > 85 ? "carnivorousmycelium" : "mycelium";
        GrowthControllerImpl.instance.growMycelium(myceliumType, ObjectRegistry.lookupName(tecton) + "-mycelium", tecton, null);
    }
}
