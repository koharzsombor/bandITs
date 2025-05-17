import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Swing-es megjelenitese egy FertileTectonnak
 */
public class SwingSemiFertileTecton extends JPanel implements Updatable, SwingTecton{

    // Hogy tudjon kommunikalni a FertileTectonImpl-vel
    private TectonView tectonView;

    // Ez fog megjelenni ha rakattintunk a tectonra
    private JPopupMenu tectonPopupMenu;

    private int size = 50;

    protected Color fillColor = Color.GRAY;

    /**
     * Konstruktor, PopupMenu + gombok + listenerek
     * @param tecton
     */
    SwingSemiFertileTecton(SemiFertileTectonImpl tecton) {
        this.tectonView =  tecton;

        tectonPopupMenu = new JPopupMenu();
        tectonPopupMenu.add("SemiFertileTecton: " + ObjectRegistry.lookupName(tecton));

        JButton growMycelium = new JButton("Grow Mycelium on this Tecton");
        growMycelium.addActionListener(new GrowMyceliumToSemiFertileActionListener(tecton));
        tectonPopupMenu.add(growMycelium);

        JButton growMushroomBody = new JButton("Grow MushroomBody on this Tecton - Attention: MushroomBodys can NOT grow on this type of tecton");
        growMushroomBody.addActionListener(new GrowMushroomBodyToSemiFertileActionListener(tecton));
        tectonPopupMenu.add(growMushroomBody);

        addMouseListener(new TectonMouseListener(this));

        update();
    }

    /**
     * Frissiti a tooltipen levo adatokat
     */
    @Override
    public void update() {
        setToolTipText("SemiFertileTecton: " + ObjectRegistry.lookupName(tectonView) + " | " +
                "Cannot grow MushroomBody on this Tecton!" + " | " +
                "Spores: " + tectonView.getSpores().size());

        ArrayList<MyceliumImpl> myceliumList = new ArrayList<>();
        for(Object o : tectonView.getMycelia().toArray()){
            myceliumList.add((MyceliumImpl) o);
        }

        if(!myceliumList.isEmpty()){
            boolean wasntCarnivorous = true;
            for(Mycelium m : myceliumList){
                if(m instanceof CarnivorousMyceliumImpl){
                    wasntCarnivorous = false;
                    add((SwingCarnivorousMycelium) ViewRepository.getView(m));
                }
            }
            if(wasntCarnivorous){
                add((SwingMycelium) ViewRepository.getView(myceliumList.get(0)));
            }
        }

        if(tectonView.getMushroomBody()!=null){
            add((SwingMushroomBody) ViewRepository.getView(tectonView.getMushroomBody()));
        }

        for(Insect i : tectonView.getOccupants()){
            add((SwingInsect) ViewRepository.getView(i));
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
