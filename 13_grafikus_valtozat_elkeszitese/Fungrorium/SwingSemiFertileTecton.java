import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

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
        Tecton location = (FertileTectonImpl) ViewRepository.getView(this);
        setToolTipText("SemiFertileTecton: " + ObjectRegistry.lookupName(location) + "\n" +
                "Cannot grow MushroomBody on this Tecton!" + "\n" +
                "Spores: " + location.getSpores().size());
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
