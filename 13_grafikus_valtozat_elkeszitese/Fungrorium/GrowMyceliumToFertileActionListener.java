import java.awt.event.ActionListener;
import java.util.Random;

public class GrowMyceliumToFertileActionListener implements ActionListener {

    private Random random;
    FertileTectonImpl tecton;

    GrowMyceliumToFertileActionListener(FertileTectonImpl tecton) {
        this.tecton = tecton;
        random = new Random();
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        //Mycelium mycelium = new MyceliumImpl(tecton);
        String myceliumType = random.nextInt(100) > 85 ? "carnivorousmycelium" : "mycelium";
        GrowthControllerImpl.instance.growMycelium(myceliumType, ObjectRegistry.lookupName(tecton) + "-mycelium", tecton, null);
    }
}
