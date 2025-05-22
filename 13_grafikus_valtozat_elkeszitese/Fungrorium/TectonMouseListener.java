import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TectonMouseListener implements MouseListener {

    SwingTecton swingTecton;

    TectonMouseListener(SwingTecton swingTecton) {
        this.swingTecton = swingTecton;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        swingTecton.showPopupMenu(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
