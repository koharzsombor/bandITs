import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TectonChoosingButtonListener implements ActionListener {
    Insect i;
    Tecton t;
    JPopupMenu popup;

    /**
     * Az InsectController osztály, aki felelős az insectek irányitásáért
     */
    InsectController insectController;

    TectonChoosingButtonListener(Insect i, Tecton t, JPopupMenu popup) {
        this.i = i;
        this.t = t;
        this.popup = popup;
        this.insectController = (InsectController) ObjectRegistry.getObject("InsectController");
    }

    public void actionPerformed(ActionEvent e) {
        insectController.move(i, t);
    }
}
