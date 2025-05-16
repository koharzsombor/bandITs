import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.List;
import java.awt.Color;

/**
 *
 */
public class GameFieldView extends JPanel {
    private List<TectonView> tectons;

    public GameFieldView() {
        setBorder(new LineBorder(Color.black));
    }

    public void SetTectons(List<TectonView> tectons) {
        this.tectons = tectons;
    }
}
