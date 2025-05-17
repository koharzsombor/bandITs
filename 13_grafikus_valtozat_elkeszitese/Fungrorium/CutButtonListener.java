import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CutButton gombhoz eseménykezelő osztály
 */
public class CutButtonListener implements ActionListener {
    /**
     * Az insect, ami tartozik a gombhoz
     */
    Insect insect;

    /**
     * Az InsectController osztály, aki felelős az insectek irányitásáért
     */
    InsectController insectController;

    /**
     * Konstruktor, ami elmenti a létrehozó insectet, és lekéri az InsectControllert késöbbi használatra
     * @param insect Insect, ami tartozik a gombhoz
     */
    CutButtonListener(Insect insect) {
        this.insect = insect;
        this.insectController = (InsectController) ObjectRegistry.getObject("InsectController");
    }

    /**
     * A tényleges működése a gombnak, azak szól az InsectControllernek, hogy az insect elvágna egy myceliumot
     * @param e feldolgozandó event
     */
    public void actionPerformed(ActionEvent e) {
        insectController.cut(insect);
    }
}