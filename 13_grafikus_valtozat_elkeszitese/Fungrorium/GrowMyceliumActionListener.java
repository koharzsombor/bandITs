import java.awt.event.ActionListener;

public class GrowMyceliumActionListener implements ActionListener {

    FertileTectonImpl fertileTecton;

    GrowMyceliumActionListener(FertileTectonImpl fertileTecton) {
        this.fertileTecton = fertileTecton;
    }

    // Ebbe egyaltalan nem vagyok biztos hogy ez igy jo-e.
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Mycelium mycelium = new MyceliumImpl(fertileTecton);
    }
}
