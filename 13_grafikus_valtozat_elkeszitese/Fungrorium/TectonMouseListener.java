import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TectonMouseListener implements MouseListener {

    SwingFertileTecton swingFertileTecton;

    TectonMouseListener(SwingFertileTecton swingFertileTecton) {
        this.swingFertileTecton = swingFertileTecton;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        swingFertileTecton.showPopupMenu(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
