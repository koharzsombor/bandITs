import javax.swing.*;

/**
 * Move gomb
 */
public class MoveButton extends JMenu {
    /**
     * Insect akihez a gomb tartozik
     */
    Insect insect;

    /**
     * Konstruktor
     * @param text gombon megjelenő szöveg
     * @param insect Insect ami tartozik a gombhoz
     */
    MoveButton(String text, Insect insect){
        super(text);
        this.insect = insect;

        add(new JLabel("Choose a neighbouring tecton to move to"));
        Tecton location = insect.getLocation();
        for(Tecton t : location.getNeighbours()){
            JMenuItem button = new JMenuItem(ObjectRegistry.lookupName(t));
            button.addActionListener(new TectonChoosingButtonListener(insect, t));
            add(button);
        }
    }
}
