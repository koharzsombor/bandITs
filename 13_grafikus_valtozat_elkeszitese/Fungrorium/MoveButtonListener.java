import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Move gombra eseménykezelő osztály
 */
public class MoveButtonListener implements ActionListener {
    /**
     * Az insect, aki létrehozta a gombot
     */
    Insect insect;

    /**
     * Az InsectController osztály, aki felelős az insectek irányitásáért
     */
    InsectController insectController;

    /**
     * Konstruktor, ami elmenti a létrehozó insectet, és lekéri az InsectControllert késöbbi használatra
     * @param insect
     */
    MoveButtonListener(Insect insect) {
        this.insect = insect;
        this.insectController = (InsectController) ObjectRegistry.getObject("InsectController");
    }

    /**
     * A tényleges működése a gombnak, azak szól az InsectControllernek, hogy az insect elvágna egy myceliumot
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        TectonView tecton;
        insectController.move(insect, tecton);
    }
}