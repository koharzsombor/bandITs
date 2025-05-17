import java.awt.event.ActionListener;

public class GrowMyceliumToFertileActionListener implements ActionListener {

    FertileTectonImpl tecton;

    GrowMyceliumToFertileActionListener(FertileTectonImpl tecton) {
        this.tecton = tecton;
    }

    // Ebbe egyaltalan nem vagyok biztos hogy ez igy jo-e.
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Mycelium mycelium = new MyceliumImpl(tecton);
    }
}
