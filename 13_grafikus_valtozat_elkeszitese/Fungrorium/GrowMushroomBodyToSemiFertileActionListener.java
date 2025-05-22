import java.awt.event.ActionListener;

/**
 * GrowMushroomBody SemiFertile Tectonra
 */
public class GrowMushroomBodyToSemiFertileActionListener implements ActionListener {

    /**
     * SemiFertileTecton, amihez tartozik
     */
    SemiFertileTectonImpl tecton;

    /**
     * Konstruktor
     * @param tecton FertileTecton
     */
    GrowMushroomBodyToSemiFertileActionListener(SemiFertileTectonImpl tecton) {
        this.tecton = tecton;
    }

    /**
     * Létrehozza a MushroomBody-t
     * @param e the event to be processed
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        //MushroomBody mushroomBody = new MushroomBodyImpl(tecton);
        GrowthControllerImpl.instance.growMushroomBody(ObjectRegistry.lookupName(tecton)+"MB", tecton, null);
    }
}
