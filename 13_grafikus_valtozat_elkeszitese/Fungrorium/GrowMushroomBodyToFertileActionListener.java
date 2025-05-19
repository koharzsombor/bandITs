import java.awt.event.ActionListener;

/**
 * GrowMushroomBody Fertile Tectonra
 */
public class GrowMushroomBodyToFertileActionListener implements ActionListener {

    /**
     * FertileTecton, amihez tartozik
     */
    FertileTectonImpl tecton;

    /**
     * Konstruktor
     * @param tecton FertileTecton
     */
    GrowMushroomBodyToFertileActionListener(FertileTectonImpl tecton) {
        this.tecton = tecton;
    }

    /**
     * Létrehozza a MushroomBody-t
     * @param e the event to be processed
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        MushroomBody mushroomBody = new MushroomBodyImpl(tecton);
    }
}
