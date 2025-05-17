import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class SwingTecton extends JPanel {
    abstract void update();

    abstract void showPopupMenu(MouseEvent e);
}
