import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.*;

/**
 * Swing-es megjelenitese egy FertileTectonnak
 */
public class SwingFertileTecton extends JPanel implements Updatable, SwingTecton{

    // Hogy tudjon kommunikalni a FertileTectonImpl-vel
    private TectonView tectonView;

    // Ez fog megjelenni ha rakattintunk a tectonra
    private JPopupMenu tectonPopupMenu;

    private int size = 50;

    protected Color fillColor = new Color(56, 93, 56);

    /**
     * Konstruktor, PopupMenu + gombok + listenerek
     * @param tecton
     */
    SwingFertileTecton(FertileTectonImpl tecton) {
        this.tectonView =  tecton;

        tectonPopupMenu = new JPopupMenu();
        tectonPopupMenu.add("FertileTecton: " + ObjectRegistry.lookupName(tecton));

        JButton growMycelium = new JButton("Grow Mycelium on this Tecton");
        growMycelium.addActionListener(new GrowMyceliumToFertileActionListener(tecton));
        tectonPopupMenu.add(growMycelium);

        JButton growMushroomBody = new JButton("Grow MushroomBody on this Tecton");
        growMushroomBody.addActionListener(new GrowMushroomBodyToFertileActionListener(tecton));
        tectonPopupMenu.add(growMushroomBody);

        addMouseListener(new TectonMouseListener(this));

        update();
    }

    /**
     * Frissiti a tooltipen levo adatokat
     */
    @Override
    public void update() {
        removeAll();
        boolean hasMB = tectonView.getMushroomBody()!=null;
        setToolTipText("FertileTecton: " + ObjectRegistry.lookupName(tectonView) + " | " +
                "Spores: " + tectonView.getSpores().size() + " | " +
                "Mycelium: " + tectonView.getMycelia().size() +  " | " +
                "MB: " +  hasMB);

        setLayout(new BorderLayout());

        System.out.println(tectonView.getMycelia().size());

        LinkedList<Mycelium> myceliumList = new LinkedList<>(tectonView.getMycelia());

        System.out.println(myceliumList.size());

        if(myceliumList.size()>0){
            boolean wasntCarnivorous = true;
            for(Mycelium m : myceliumList){
                if(m instanceof CarnivorousMyceliumImpl){
                    wasntCarnivorous = false;
                    add(ViewRepository.getPanel(m));
                    break;
                }
            }
            if(wasntCarnivorous){
                add(ViewRepository.getPanel(myceliumList.get(0)));
            }
        }

        if(tectonView.getMushroomBody()!=null){
            add((SwingMushroomBody) ViewRepository.getView(tectonView.getMushroomBody()));
        }

        for(int i=0; i<tectonView.getOccupants().size(); i++){
            SwingInsect si = (SwingInsect) ViewRepository.getView(tectonView.getOccupants().get(i));
            si.setLocation(new Point(i%5, i/5));
            add(si);
        }
    }

    public void showPopupMenu(MouseEvent e) {
        tectonPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Maga a kocka
        g2.setColor(fillColor);
        g2.fillRect(0, 0, size, size);

        // Kocka keretje
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, size, size);

        g2.dispose();
    }

}
