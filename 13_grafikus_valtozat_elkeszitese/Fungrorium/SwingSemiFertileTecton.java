import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Swing-es megjelenitese egy SemiFertileTectonnak
 */
public class SwingSemiFertileTecton extends JPanel implements Updatable, SwingTecton{

    /**
     * Tecton, amihez tartozik a panel
     */
    private TectonView tectonView;

    /**
     * Panelhez tartozó JPopupMenu
     */
    private JPopupMenu tectonPopupMenu;

    /**
     * Tecton panel mérete
     */
    private int size = 50;

    /**
     * Tecton szine
     */
    private Color tectonColor = new Color(128, 128, 128);

    /**
     * Mycelium & CarnivorousMycelium szine
     */
    private Color myceliumColor = new Color(166, 80, 42);
    private Color carnivorousMyceliumColor = new Color(255, 88, 170);

    /**
     * MushroomBody méretei
     */
    private int MB_TRIANGLE_MARGIN = 1;
    private int MB_TRIANGLE_MARGIN_SIDES = 6;
    private int MB_width = 50;
    private int MB_height = 42;
    private int MB_nPoints = 3;

    /**
     * MushroomBody szine
     */
    private Color mushroomBodyColor = new Color(255,0,0);

    /**
     * Insect mérete
     */
    private int insectSize = 10;

    /**
     * Insect szine
     */
    private Color insectColor = new Color(0,0,0);

    /**
     * Konstruktor
     * @param tecton amihez tartozik
     */
    SwingSemiFertileTecton(SemiFertileTectonImpl tecton) {
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
        setToolTipText("SemiFertileTecton: " + ObjectRegistry.lookupName(tectonView) + " | " +
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

    /**
     * Lefrissiti és előhozza a PopupMenu-t
     * @param e MouseEvent, aminek hatására elöjött - előhozás helyéhez kell
     */
    public void showPopupMenu(MouseEvent e) {
        tectonPopupMenu.removeAll();

        String name = "SemiFertileTecton: " + ObjectRegistry.lookupName(tectonView);
        tectonPopupMenu.add(new JLabel(name));

        for(Insect i : tectonView.getOccupants()) {
           //ViewRepository.getView(i).update();
            JMenuItem insectPanel = (JMenuItem) ViewRepository.getView(i);
            tectonPopupMenu.add(insectPanel);
        }

        if(tectonView.getMushroomBody()!=null) {
            //ViewRepository.getView(tectonView.getMushroomBody()).update();
            JMenuItem mushroomBodyPanel = (JMenuItem) ViewRepository.getView(tectonView.getMushroomBody());
            tectonPopupMenu.add(mushroomBodyPanel);
        }

        SwingTectonButton tectonButton = new SwingTectonButton(name, tectonView, false, "No Mushroombody can grow on this tecton.");
        tectonPopupMenu.add(tectonButton);

        tectonPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Megnézi, hogy van-e és hogy milyen Mycelium van a TectonView-on
     * 0, ha nincs
     * 1, ha Mycelium
     * 2, ha Carnivorous
     * @return int, Mycelium tipusa szerint
     */
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

    /**
     * Kirajzolja a tectont
     * -> Myceliumot
     * -> MushroomBody-t
     * -> Insecteket
     * -> Keretet ad az egésznek
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        //update();
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
