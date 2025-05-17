import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EatButton gombhoz eseménykezelő osztály
 */
public class EatButtonListener implements ActionListener {
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
    EatButtonListener(Insect insect) {
        this.insect = insect;
        this.insectController = (InsectController) ObjectRegistry.getObject("InsectController");
    }

    /**
     * A tényleges működése a gombnak, azak szól az InsectControllernek, hogy az insect megenne egy spórát
     * @param e feldolgozandó event
     */
    public void actionPerformed(ActionEvent e) {
        insectController.eat(insect);
    }
}