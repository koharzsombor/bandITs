import java.awt.event.ActionListener;

public class GrowMyceliumToSemiFertileActionListener implements ActionListener {

    SemiFertileTectonImpl tecton;

    GrowMyceliumToSemiFertileActionListener(SemiFertileTectonImpl tecton) {
        this.tecton = tecton;
    }

    // Ebbe egyaltalan nem vagyok biztos hogy ez igy jo-e.
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Mycelium mycelium = new MyceliumImpl(tecton);
    }
}
