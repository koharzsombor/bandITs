import java.awt.event.ActionListener;

public class GrowMushroomBodyToSemiFertileActionListener implements ActionListener {

    SemiFertileTectonImpl tecton;

    GrowMushroomBodyToSemiFertileActionListener(SemiFertileTectonImpl tecton) {
        this.tecton = tecton;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        MushroomBody mushroomBody = new MushroomBodyImpl(tecton);
    }
}
