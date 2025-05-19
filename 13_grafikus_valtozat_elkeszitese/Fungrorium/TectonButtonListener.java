import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Legacy code
 */
public class TectonButtonListener implements ActionListener {
    /**
     * SwingButton, amihez tartozott
     */
    SwingTectonButton button;

    /**
     * Konstruktor
     * @param button
     */
    TectonButtonListener(SwingTectonButton button){
        this.button = button;
    }

    /**
     * ActionPerfomed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        //button.showPopupMenu();
    }
}
