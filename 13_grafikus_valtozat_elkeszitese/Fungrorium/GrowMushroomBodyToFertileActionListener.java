import java.awt.event.ActionListener;

public class GrowMushroomBodyToFertileActionListener implements ActionListener {

    FertileTectonImpl tecton;

    GrowMushroomBodyToFertileActionListener(FertileTectonImpl tecton) {
        this.tecton = tecton;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        MushroomBody mushroomBody = new MushroomBodyImpl(tecton);
    }
}
