import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TectonButtonListener implements ActionListener {
    SwingTectonButton button;

    TectonButtonListener(SwingTectonButton button){
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        button.showPopupMenu();
    }
}
