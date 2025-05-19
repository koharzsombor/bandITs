import java.awt.event.ActionListener;
import java.util.Random;

public class GrowMyceliumToSemiFertileActionListener implements ActionListener {

    SemiFertileTectonImpl tecton;

    private Random random;

    GrowMyceliumToSemiFertileActionListener(SemiFertileTectonImpl tecton) {
        this.tecton = tecton;
        random = new Random();
    }

    // Ebbe egyaltalan nem vagyok biztos hogy ez igy jo-e.
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String myceliumType = random.nextInt(100) > 85 ? "carnivorousmycelium" : "mycelium";
        GrowthControllerImpl.instance.growMycelium(myceliumType, ObjectRegistry.lookupName(tecton) + "-mycelium", tecton, null);
    }
}
