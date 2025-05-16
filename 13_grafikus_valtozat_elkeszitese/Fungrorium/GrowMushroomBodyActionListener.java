import java.awt.event.ActionListener;

public class GrowMushroomBodyActionListener implements ActionListener {

    FertileTectonImpl fertileTecton;

    GrowMushroomBodyActionListener(FertileTectonImpl fertileTecton) {
        this.fertileTecton = fertileTecton;
    }

    //
    public void actionPerformed(java.awt.event.ActionEvent e) {
        MushroomBody mushroomBody = new MushroomBodyImpl(fertileTecton);

    }
}
