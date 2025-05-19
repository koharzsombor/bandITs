import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TectonChoosingButton gombhoz eseménykezelő osztály
 */
public class TectonChoosingButtonListener implements ActionListener {
    /**
     * Insect, ami tartozik a gombhoz
     */
    Insect i;

    /**
     * Tecton, ami tartozik a gombhoz
     */
    Tecton t;

    /**
     * Az InsectController osztály, aki felelős az insectek irányitásáért
     */
    InsectController insectController;

    /**
     * Konstruktor
     * @param i Insect, ami tartozik a gombhoz
     * @param t Tecton, ami tartozik a gombhoz
     */
    TectonChoosingButtonListener(Insect i, Tecton t) {
        this.i = i;
        this.t = t;
        this.insectController = (InsectController) ObjectRegistry.getObject("InsectController");
    }

    /**
     * A tényleges működése a gombnak, azaz szól az InsectControllernek hogy az insect mozogna t tectonra
     * @param e feldolgozandó event
     */
    public void actionPerformed(ActionEvent e) {
        insectController.move(i, t);
    }
}
