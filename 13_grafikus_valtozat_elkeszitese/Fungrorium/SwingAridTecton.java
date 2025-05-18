import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Swing-es megjelenitese egy FertileTectonnak
 */
public class SwingAridTecton extends JPanel implements Updatable, SwingTecton{

    // Hogy tudjon kommunikalni a FertileTectonImpl-vel
    private TectonView tectonView;

    // Ez fog megjelenni ha rakattintunk a tectonra
    private JPopupMenu tectonPopupMenu;

    private int size = 50;

    private Color tectonColor = new Color(255, 255, 0);

    private Color myceliumColor = new Color(166, 80, 42);
    private Color carnivorousMyceliumColor = new Color(255, 88, 170);

    private int MB_TRIANGLE_MARGIN = 1;
    private int MB_TRIANGLE_MARGIN_SIDES = 6;
    private int MB_width = 50;
    private int MB_height = 42;
    private int MB_nPoints = 3;
    private Color mushroomBodyColor = new Color(255,0,0);

    private int insectSize = 10;
    private Color insectColor = new Color(0,0,0);

    /**
     * Konstruktor, PopupMenu + gombok + listenerek
     * @param tecton
     */
    SwingAridTecton(AridTectonImpl tecton) {
        this.tectonView =  tecton;

        tectonPopupMenu = new JPopupMenu();

        addMouseListener(new TectonMouseListener(this));

        update();
    }

    /**
     * Frissiti a tooltipen levo adatokat
     */
    @Override
    public void update() {
        setToolTipText("AridTecton: " + ObjectRegistry.lookupName(tectonView) + " | " +
                "Spores: " + tectonView.getSpores().size() + " | " +
                "Mycelia: " + tectonView.getMycelia().size());
        /*
        for(Insect i : tectonView.getOccupants()) {
            ViewRepository.getView(i).update();
        }
        if(tectonView.getMushroomBody()!=null) {
            ViewRepository.getView(tectonView.getMushroomBody()).update();
        }
         */
        repaint();
    }

    public void showPopupMenu(MouseEvent e) {
        tectonPopupMenu.removeAll();

        String name = "AridTecton: " + ObjectRegistry.lookupName(tectonView);
        tectonPopupMenu.add(new JLabel(name));

        for(Insect i : tectonView.getOccupants()) {
            //ViewRepository.getView(i).update();
            JButton insectPanel = ViewRepository.getButton(i);
            tectonPopupMenu.add(insectPanel);
        }

        if(tectonView.getMushroomBody()!=null) {
            //ViewRepository.getView(tectonView.getMushroomBody()).update();
            JButton mushroomBodyPanel = ViewRepository.getButton(tectonView.getMushroomBody());
            tectonPopupMenu.add(mushroomBodyPanel);
        }

        SwingTectonButton tectonButton = new SwingTectonButton(name, tectonView, true, "Any mycelia on this tecton will decay in 5 rounds.");
        tectonPopupMenu.add(tectonButton);

        tectonPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    private int hasCarnivorousMycelium() {
        LinkedList<Mycelium> myceliumList = new LinkedList<>(tectonView.getMycelia());
        if(myceliumList.size()==0) return 0;
        for (Mycelium m : myceliumList) {
            if (m instanceof CarnivorousMyceliumImpl) {
                return 2;
            }
        }
        return 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        update();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Maga a kocka
        g2.setColor(tectonColor);
        g2.fillRect(0, 0, size, size);

        //Mycelium
        int myceliumType = hasCarnivorousMycelium(); //0-no Mycelium, 1-Mycelium, 2-CarnivorousMycelium
        if(myceliumType!=0) {
            if(myceliumType==2) {
                g.setColor(carnivorousMyceliumColor);
            }
            else {
                g.setColor(myceliumColor);
            }
            g.fillOval(0, 0, size, size);
        }

        //MushroomBody
        if(tectonView.getMushroomBody()!=null) {
            g.setColor(mushroomBodyColor);
            int[] xPoints = {MB_width / 2, MB_width - MB_TRIANGLE_MARGIN_SIDES, MB_TRIANGLE_MARGIN_SIDES};
            int[] yPoints = {MB_TRIANGLE_MARGIN, MB_height - MB_TRIANGLE_MARGIN, MB_height - MB_TRIANGLE_MARGIN};
            g2.fillPolygon(xPoints, yPoints, MB_nPoints);
        }

        //Insect
        g2.setColor(insectColor);
        for(int i=0;i<tectonView.getOccupants().size(); i++){
            int colSize = size/5;
            int rowSize = size/5;
            int x = colSize * (i%5);
            int y = rowSize * (i/5);
            g2.fillOval(x, y, insectSize, insectSize);
        }

        // Kocka keretje
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, size, size);

        g2.dispose();
    }

}
